package com.mybank.shared.infrastructure.persistence

import com.mybank.account.domain.{ Account, AccountId, AccountRepository }
import com.mybank.shared.domain.Singleton
import com.mybank.shared.infrastructure.persistence.models.AccountsTable.AccountsTable
import com.mybank.shared.infrastructure.persistence.models.{ Accounts, Transactions, TransactionsTable }
import com.mybank.transaction.domain.{ Transaction, TransactionRepository }
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
final class PostgrestDatabase extends AccountRepository with TransactionRepository {
  val db = Database.forConfig( "mydb" )
  
  override def search( accountId: AccountId ): Future[ Option[ Account ] ] = {
    val actions = for {
      accountRows <- TableQuery[ AccountsTable ].filter( _.id === accountId.value ).result.headOption
      accounts = accountRows.map( row => Account( row.id, row.number, row.amount ) )
    } yield accounts
    db.run( actions.transactionally )
  }
  
  override def update( account: Account ): Future[ Unit ] = {
    val actions = for {
      _ <- TableQuery[ AccountsTable ].filter( _.id === account.id.value )
                                      .update( account.toAccountTable )
    } yield ()
    db.run( actions.transactionally )
  }
  
  override def createTransaction( transaction: Transaction ): Future[ Unit ] = {
    val actions = for {
      _ <- TableQuery[ TransactionsTable ] += transaction.toTable
    } yield ()
    db.run( actions.transactionally )
  }
  
  override def searchTransactions( accountId: AccountId ): Future[ Seq[ Transaction ] ] = {
    val actions = for {
      transactionRows <- TableQuery[ TransactionsTable ].filter( _.accountId === accountId.value ).result
      accounts = transactionRows
        .map( row => Transaction( row.id, row.accountId, row.amount, row.description, row.date ) )
    } yield accounts
    
    db.run( actions.transactionally )
  }
  
  implicit class toTable( transaction: Transaction ) {
    def toTable: Transactions = {
      Transactions(
        transaction.id.value,
        transaction.accountId.value,
        transaction.amount.value,
        transaction.description.toString,
        transaction.date.value
      )
    }
  }
  
  implicit class toAccountTable( account: Account ) {
    def toAccountTable: Accounts = Accounts( account.id.value, account.number.value, account.amount.value )
  }
  
}
