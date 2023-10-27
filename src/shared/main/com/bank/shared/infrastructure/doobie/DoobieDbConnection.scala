package com.bank.shared.infrastructure.doobie

import cats.effect._
import cats.effect.unsafe.implicits.global
import doobie._
import doobie.syntax.ConnectionIOOps
import doobie.util.transactor.Transactor.Aux

import scala.concurrent.Future

// Very important to deal with arrays

final class DoobieDbConnection( dbConfig: JdbcConfig) {
  
  val transactor: Aux[ IO, Unit ] = Transactor.fromDriverManager[ IO ](
    dbConfig.driver,
    dbConfig.url,
    dbConfig.user,
    dbConfig.password
  )
  
  def read[T](query: ConnectionIOOps[T]): Future[T] = query.transact(transactor).unsafeToFuture()
  
}
