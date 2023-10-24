package com.bank.shared.infrastructure.dependency_injection

import akka.actor.ActorSystem
import akka.stream.Materializer
import com.bank.shared.domain.bus.command.{ Command, CommandHandler }
import com.bank.shared.domain.bus.query.{ Query, QueryHandler, Response }
import com.bank.shared.domain.logger.Logger
import com.bank.shared.infrastructure.bus.event.DomainEventSubscribersInformation
import com.bank.shared.infrastructure.bus.event.akka.AkkaApplicationEventBus
import com.bank.shared.infrastructure.doobie.{ DoobieDbConnection, JdbcConfig }
import com.bank.shared.infrastructure.logger.ScalaLoggingLogger

import scala.collection.mutable
import scala.concurrent.ExecutionContext

final class SharedModuleDependencyContainer(
  actorSystemName: String,
  dbConfig:        JdbcConfig,
) {
  
  val commands: mutable.Seq[ CommandHandler[ Command ] ] = mutable.Seq.empty
  val queries: mutable.Seq[ QueryHandler[ Query, Response ] ] = mutable.Seq.empty
  
  implicit val actorSystem: ActorSystem = ActorSystem( actorSystemName )
  val materializer    : Materializer     = Materializer( actorSystem )
  val executionContext: ExecutionContext = actorSystem.dispatcher
  
  implicit val doobieDbConnection: DoobieDbConnection = new DoobieDbConnection( dbConfig )
  
  val domainEvent = new DomainEventSubscribersInformation
  implicit val eventBus: AkkaApplicationEventBus = new AkkaApplicationEventBus( actorSystem, domainEvent)
  
  val logger: Logger = new ScalaLoggingLogger
}
