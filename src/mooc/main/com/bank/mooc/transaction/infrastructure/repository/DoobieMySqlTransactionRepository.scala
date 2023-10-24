package com.bank.mooc.transaction.infrastructure.repository

import com.bank.mooc.account.domain.AccountId
import com.bank.mooc.transaction.domain.{ Transaction, TransactionAmount, TransactionDate, TransactionId, TransactionRepository }
import com.bank.shared.infrastructure.doobie.DoobieDbConnection
import doobie.implicits._
import doobie.util.Read

import scala.concurrent.{ ExecutionContext, Future }

final class DoobieMySqlTransactionRepository( implicit db: DoobieDbConnection, executionContext: ExecutionContext )
  extends TransactionRepository {
  
  implicit val transactionRead: Read[ Transaction ] =
    Read[ (TransactionId, AccountId, TransactionAmount, String, TransactionDate) ].map{
      case (id, accountId, amount, description, date) =>
        Transaction( id, accountId, amount, description, date )
    }
    
  override def allT( ): Future[ Seq[ Transaction ] ] = {
    db.read( sql"SELECT id, account_id, amount, description, date FROM transactions".query[ Transaction ].to[ Seq ] )
  }
  
  override def save( transaction: Transaction ): Future[ Unit ] = {
    sql"INSERT INTO transactions(id, account_id, description, date) VALUES (${ transaction.id }, ${ transaction.accountId }, ${ transaction.description }, ${ transaction.date })"
        .update.run
        .transact( db.transactor )
        .unsafeToFuture( )
        .map( _ => () )
  }
  
  override def searchT( accountId: AccountId ): Future[ Seq[ Transaction ] ] = Future( Seq.empty)
}
