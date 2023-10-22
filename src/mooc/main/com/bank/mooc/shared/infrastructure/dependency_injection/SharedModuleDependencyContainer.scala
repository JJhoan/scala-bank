package com.bank.mooc.shared.infrastructure.dependency_injection

import akka.actor.ActorSystem
import com.bank.shared.domain.logger.Logger
import com.bank.shared.infrastructure.doobie.{ DoobieDbConnection, JdbcConfig }
import com.bank.shared.infrastructure.logger.ScalaLoggingLogger

import scala.concurrent.ExecutionContext


final class SharedModuleDependencyContainer(
    actorSystemName: String,
    dbConfig:        JdbcConfig
) {
  implicit val actorSystem: ActorSystem = ActorSystem( actorSystemName)
  val executionContext: ExecutionContext = actorSystem.dispatcher

  val doobieDbConnection: DoobieDbConnection = new DoobieDbConnection( dbConfig)

  val logger: Logger = new ScalaLoggingLogger
}
