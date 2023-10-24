package com.bank.shared.infrastructure.doobie

import cats.effect.{ ContextShift, IO }
import doobie.Transactor
import doobie.syntax.ConnectionIOOps
import doobie.util.transactor.Transactor.Aux

import scala.concurrent.{ ExecutionContext, Future }

final class DoobieDbConnection(dbConfig: JdbcConfig){
  implicit val cs: ContextShift[IO] = IO.contextShift( ExecutionContext.global)
  
  val transactor: Aux[IO, Unit] = Transactor.fromDriverManager[IO](
    dbConfig.driver,
    dbConfig.url,
    dbConfig.user,
    dbConfig.password
  )

  def read[T](query: ConnectionIOOps[T]): Future[T] = query.transact(transactor).unsafeToFuture()
}
