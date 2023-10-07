package com.mybank.transaction.domain

import com.mybank.account.domain.AccountId

import scala.concurrent.Future

trait TransactionRepository {
  
  def createTransaction( transaction: Transaction ): Future[ Unit ]
  def searchTransactions( accountId: AccountId ): Future[ Seq[ Transaction ] ]
  
}
