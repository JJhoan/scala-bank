package com.bank.mooc.transaction.infrastructure.repository

import cats.effect.unsafe.implicits.global
import com.bank.mooc.account.domain.AccountId
import com.bank.mooc.shared.infrastructure.doobie.TypesConversions._
import com.bank.mooc.transaction.domain.{ Transaction, TransactionRepository }
import com.bank.shared.infrastructure.doobie.DoobieDbConnection
import doobie.implicits._

import scala.concurrent.{ ExecutionContext, Future }

final class DoobieMySqlTransactionRepository( implicit db: DoobieDbConnection, executionContext: ExecutionContext )
  extends TransactionRepository {
  
  override def allT( ): Future[ Seq[ Transaction ] ] = {
    sql"SELECT id, account_id, amount, description, date FROM transactions"
      .query[ Transaction ]
      .to[ Seq ]
      .transact( db.transactor ).unsafeToFuture( )
  }
  
  override def save( transaction: Transaction ): Future[ Unit ] = {
    sql"INSERT INTO transactions(id, account_id, description, date) VALUES (${ transaction.id }, ${ transaction.accountId }, ${ transaction.description }, ${ transaction.date })"
        .update.run
        .transact( db.transactor )
        .unsafeToFuture( )
        .map( _ => () )
    Future successful()
  }
  
  override def searchT( accountId: AccountId ): Future[ Seq[ Transaction ] ] = Future( Seq.empty )
}
