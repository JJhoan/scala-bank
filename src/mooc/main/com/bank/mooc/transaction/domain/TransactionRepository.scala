package com.bank.mooc.transaction.domain

import com.bank.mooc.account.domain.AccountId

import scala.concurrent.Future

trait TransactionRepository {
  
  def save( transaction: Transaction ): Future[ Unit ]
  def allT( ): Future[ Seq[ Transaction ] ]
  def searchT( accountId: AccountId ): Future[ Seq[ Transaction ] ]
  
}
