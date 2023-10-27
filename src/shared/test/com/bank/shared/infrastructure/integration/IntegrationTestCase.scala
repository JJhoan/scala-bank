package com.bank.shared.infrastructure.integration

import com.bank.shared.domain.bus.command.CommandBus
import com.bank.shared.domain.bus.query.QueryBus
import com.bank.shared.domain.logger.Logger
import com.bank.shared.infrastructure.bus.command.{ CommandHandlerInformation, InMemoryCommandBus }
import com.bank.shared.infrastructure.bus.event.akka.AkkaApplicationEventBus
import com.bank.shared.infrastructure.bus.query.{ InMemoryQueryBus, QueryHandlerInformation }
import com.bank.shared.infrastructure.dependency_injection.SharedModuleDependencyContainer
import com.bank.shared.infrastructure.doobie.{ DoobieDbConnection, JdbcConfig }
import com.bank.shared.infrastructure.unit.UnitTestCase

import scala.concurrent.ExecutionContext
import com.typesafe.config.ConfigFactory

trait IntegrationTestCase extends UnitTestCase {
  private val actorSystemName = "cqrs-ddd-scala-example-integration-test"
  
  private val appConfig = ConfigFactory.load( "application" )
  private val dbConfig  = JdbcConfig( appConfig.getConfig( "database" ) )
  
  private val sharedDependencies = new SharedModuleDependencyContainer( actorSystemName, dbConfig )
  
  implicit val queryBus  : QueryBus   = new InMemoryQueryBus( new QueryHandlerInformation, sharedDependencies )
  implicit val commandBus: CommandBus = new InMemoryCommandBus( new CommandHandlerInformation, sharedDependencies )
  
  implicit protected val executionContext: ExecutionContext = sharedDependencies.executionContext
  implicit val eventBus: AkkaApplicationEventBus = sharedDependencies.eventBus
  
  implicit protected val doobieDbConnection: DoobieDbConnection = sharedDependencies.doobieDbConnection
  protected          val logger            : Logger             = sharedDependencies.logger
}
