package com.bank.mooc.account.infrastructure.repository

import cats.effect.unsafe.implicits.global
import com.bank.mooc.account.domain.AccountMother
import com.bank.mooc.account.infrastructure.AccountIntegrationTestCase
import doobie.implicits._
import org.scalatest.BeforeAndAfterEach
import org.scalatest.concurrent.Eventually._

import scala.concurrent.duration._
import scala.concurrent.{ Await, Future }

final class DoobieMySqlAccountRepositoryShould extends AccountIntegrationTestCase with BeforeAndAfterEach {
  private def cleanUsersTable( ): Future[Int] = {
    sql"TRUNCATE TABLE accounts"
      .update.run
      .transact( doobieDbConnection.transactor )
      .unsafeToFuture( )
      //.map( _ => println( "xDD" ) )
      //.futureValue
  }
  
  override protected def beforeEach( ): Unit = {
    super.beforeEach( )
    Await.result(cleanUsersTable(), 5.seconds)
  }
  
  "return an empty sequence if there're no users" in {
    repository.all( ).futureValue shouldBe Seq.empty
  }
  
  "search all existing users" in {
    val users = AccountMother.randomSeq
    
    users.foreach( u => repository.save( u ).futureValue )
    
    println( repository.all( ).futureValue )
    
    repository.all( ).futureValue shouldBe users
  }
}
