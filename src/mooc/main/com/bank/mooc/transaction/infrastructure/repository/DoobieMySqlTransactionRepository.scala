package com.bank.mooc.transaction.infrastructure.repository

import com.bank.mooc.account.domain.{ Account, AccountId, AccountRepository }
import com.bank.mooc.transaction.domain.{ Transaction, TransactionRepository }
import com.bank.shared.infrastructure.doobie.DoobieDbConnection
import doobie.implicits._

import scala.concurrent.{ ExecutionContext, Future }

final class DoobieMySqlTransactionRepository( implicit db: DoobieDbConnection, executionContext: ExecutionContext )
  extends TransactionRepository {
  override def allT( ): Future[ Seq[ Transaction ] ] = {
    db.read( sql"SELECT * FROM transactions".query[ Transaction ].to[ Seq ] )
  }
  
  override def save( transaction: Transaction ): Future[ Unit ] = {
    sql"INSERT INTO transactions(id, account_id, description, date) " +
      sql"VALUES (${ transaction.id }, ${ transaction.accountId }, ${ transaction.description }, ${ transaction.date })"
        .update.run
        .transact( db.transactor )
        .unsafeToFuture( )
        .map( _ => () )
  }
  
  override def searchT( accountId: AccountId ): Future[ Seq[ Transaction ] ] = Future( Seq.empty)
}
