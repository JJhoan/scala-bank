package com.mybank.shared.infrastructure.database

import com.mybank.account.application.{ AccountSearchable, AccountUpdatable }
import com.mybank.account.domain.{ Account, AccountId }
import com.mybank.transaction.application.create.TransactionCreatable
import com.mybank.transaction.application.find.TransactionsSearchable
import com.mybank.transaction.domain.Transaction

import javax.inject.Singleton
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class LocalDataBase extends AccountSearchable with AccountUpdatable with TransactionCreatable with TransactionsSearchable {
  private val accounts: scala.collection.mutable.Set[ Account ] = scala.collection.mutable.Set(
    Account( 1, "1234-6661-1234", 500000.00 ),
    Account( 2, "1532-6661-4523", 150000.00 ),
    Account( 3, "1234-2346-6982", 100000.00 ),
  )
  
  private val transactions: scala.collection.mutable.Set[ Transaction ] = scala.collection.mutable.Set( )
  
  override def searchAccount( accountId: Int ): Future[ Option[ Account ] ] = {
    Future( accounts.find( _.id.value == accountId ) )
  }
  
  override def updateAccount( account: Account ): Future[ Unit ] = {
    Future{
      if ( !accounts.contains( account ) ) throw new NullPointerException( )
      accounts.remove( account )
      accounts.add( account )
    }
  }
  
  override def createTransaction( transaction: Transaction ): Future[ Unit ] = Future( transactions += transaction )
  
  override def searchTransactions( accountId: AccountId ): Future[ Seq[ Transaction ] ] = {
    Future( transactions.filter( _.accountId.equals( accountId ) ).toSeq )
  }
}
