package com.bank.mooc.transaction.application.find

import com.bank.mooc.account.domain.AccountId
import com.bank.mooc.transaction.domain.TransactionRepository

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

final class TransactionFinder( repository: TransactionRepository ) {
  
  def find( accountId: AccountId ): Future[ TransactionsResponse ] = {
    repository.searchT( accountId ) map ( TransactionsResponse( _ ) )
  }
  
}
