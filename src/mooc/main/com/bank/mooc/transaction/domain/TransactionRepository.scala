package com.bank.mooc.transaction.domain

import com.bank.mooc.account.domain.AccountId

import scala.concurrent.Future

trait TransactionRepository {
  
  def createTransaction( transaction: Transaction ): Future[ Unit ]
  def searchTransactions( accountId: AccountId ): Future[ Seq[ Transaction ] ]
  
}
