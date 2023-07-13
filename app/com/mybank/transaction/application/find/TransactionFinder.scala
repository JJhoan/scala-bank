package com.mybank.transaction.application.find

import com.google.inject.Inject
import com.mybank.account.domain.AccountId
import com.mybank.shared.domain.Singleton

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
final class TransactionFinder @Inject( )( transactionSearchable: TransactionsSearchable ) {
  
  def find( accountId: AccountId ): Future[ TransactionsResponse ] = {
    for {
      transactions <- transactionSearchable.searchTransactions( accountId )
    } yield TransactionsResponse( transactions )
  }
  
}
