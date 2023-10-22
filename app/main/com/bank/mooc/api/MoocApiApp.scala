package com.bank.mooc.api

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.{ ActorMaterializer, Materializer }
import com.bank.mooc.account.application.deposite_money.{ MoneyDepositor, MoneyDepositorCommandHandler }
import com.bank.mooc.account.application.find.{ AccountFinder, AccountFinderQueryHandler }
import com.bank.mooc.account.application.send_money.MoneySender
import com.bank.mooc.account.application.withdraw_money.MoneyWithdrawal
import com.bank.mooc.account.domain.AccountRepository
import com.bank.mooc.account.infrastructure.dependency_injection.AccountModuleDependencyContainer
import com.bank.mooc.account.infrastructure.repository.DoobieMySqlAccountRepository
import com.bank.shared.infrastructure.bus.command.InMemoryCommandBus
import com.bank.shared.infrastructure.bus.event.DomainEventSubscribersInformation
import com.bank.shared.infrastructure.bus.event.akka.AkkaApplicationEventBus
import com.bank.shared.infrastructure.bus.query.InMemoryQueryBus
import com.bank.shared.infrastructure.dependency_injection.SharedModuleDependencyContainer
import com.bank.shared.infrastructure.doobie.JdbcConfig
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContext
import scala.io.StdIn

object MoocApiApp {
  def start(): Unit = {
    val appConfig    = ConfigFactory.load("application")
    val serverConfig = ConfigFactory.load("http-server")

    val actorSystemName = appConfig.getString("main-actor-system.name")
    val host            = serverConfig.getString("http-server.host")
    val port            = serverConfig.getInt("http-server.port")

    val dbConfig        = JdbcConfig( appConfig.getConfig("database"))
    val domainEvent = new DomainEventSubscribersInformation
    
    val repository: AccountRepository = new DoobieMySqlAccountRepository( )
    
    val eventBus = new AkkaApplicationEventBus(actorSystemName, domainEvent)
    val queryBus = new InMemoryQueryBus()
    val commandBus = new InMemoryCommandBus()

    val sharedDependencies = new SharedModuleDependencyContainer(actorSystemName, dbConfig, eventBus)

    implicit val system: ActorSystem                = sharedDependencies.actorSystem
    implicit val materializer: Materializer         = sharedDependencies.materializer
    implicit val executionContext: ExecutionContext = sharedDependencies.executionContext

    val container = new EntryPointDependencyContainer(
      new AccountModuleDependencyContainer(sharedDependencies.doobieDbConnection, sharedDependencies.messagePublisher),
      new VideoModuleDependencyContainer(sharedDependencies.doobieDbConnection, sharedDependencies.messagePublisher)
    )

    val routes = new Routes(container)

    val bindingFuture = Http().bindAndHandle(routes.all, host, port)

    bindingFuture.failed.foreach { t =>
      println(s"Failed to bind to http://$host:$port/:")
      pprint.log(t)
    }

    // let it run until user presses return
    println(s"Server online at http://$host:$port/\nPress RETURN to stop...")

    StdIn.readLine()

    println("Stopping server...")

    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => {
        sharedDependencies.actorSystem.terminate()

        println("Server stopped!")
      })
  }
}
