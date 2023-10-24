package com.bank.mooc.api

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.Materializer
import com.bank.mooc.account.infrastructure.dependency_injection.AccountModuleDependencyContainer
import com.bank.mooc.transaction.infrastructure.dependency_injection.TransactionModuleDependencyContainer
import com.bank.shared.domain.bus.command.{ Command, CommandHandler }
import com.bank.shared.infrastructure.bus.command.{ CommandHandlerInformation, InMemoryCommandBus }
import com.bank.shared.infrastructure.bus.event.akka.AkkaApplicationEventBus
import com.bank.shared.infrastructure.bus.query.{ InMemoryQueryBus, QueryHandlerInformation }
import com.bank.shared.infrastructure.dependency_injection.SharedModuleDependencyContainer
import com.bank.shared.infrastructure.doobie.{ DoobieDbConnection, JdbcConfig }
import com.typesafe.config.ConfigFactory

import scala.collection.mutable
import scala.concurrent.ExecutionContext
import scala.io.StdIn

object MoocApiApp {
  
  val commands: mutable.Seq[ CommandHandler[ Command ] ]
  
  def start( ): Unit = {
    val appConfig    = ConfigFactory.load( "application" )
    val serverConfig = ConfigFactory.load( "http-server" )
    
    val actorSystemName = appConfig.getString( "main-actor-system.name" )
    val host            = serverConfig.getString( "http-server.host" )
    val port            = serverConfig.getInt( "http-server.port" )
    
    val dbConfig = JdbcConfig( appConfig.getConfig( "database" ) )
    
    implicit val queryBus  : InMemoryQueryBus   = new InMemoryQueryBus( new QueryHandlerInformation )
    implicit val commandBus: InMemoryCommandBus = new InMemoryCommandBus( new CommandHandlerInformation )
    
    val sharedDependencies: SharedModuleDependencyContainer = new SharedModuleDependencyContainer( actorSystemName,
      dbConfig )
    
    implicit val doobieDbConnection: DoobieDbConnection      = sharedDependencies.doobieDbConnection
    implicit val eventBus          : AkkaApplicationEventBus = sharedDependencies.eventBus
    
    val accountDependencies     = new AccountModuleDependencyContainer
    val transactionDependencies = new TransactionModuleDependencyContainer
    
    sharedDependencies.commands.appended( accountDependencies.commands )
    sharedDependencies.commands.appended( transactionDependencies.commands )
    
    sharedDependencies.queries.appended( transactionDependencies.queries )
    sharedDependencies.queries.appended( transactionDependencies.queries )
    
    implicit val system          : ActorSystem      = sharedDependencies.actorSystem
    implicit val materializer    : Materializer     = sharedDependencies.materializer
    implicit val executionContext: ExecutionContext = sharedDependencies.executionContext
    
    val container = new EntryPointDependencyContainer( queryBus, commandBus )
    
    val routes = new Routes( container )
    
    val bindingFuture = Http( ).newServerAt( host, port ).bind( routes.all )
    
    bindingFuture.failed.foreach{ t =>
      println( s"Failed to bind to http://$host:$port/:" )
    }
    
    // let it run until user presses return
    println( s"Server online at http://$host:$port/\nPress RETURN to stop..." )
    
    StdIn.readLine( )
    
    println( "Stopping server..." )
    
    bindingFuture
      .flatMap( _.unbind( ) )
      .onComplete( _ => {
        sharedDependencies.actorSystem.terminate( )
        
        println( "Server stopped!" )
      } )
  }
}
