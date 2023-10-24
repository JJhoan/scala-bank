package com.bank.mooc.transaction.application.find

import com.bank.mooc.account.domain.AccountId
import com.bank.mooc.transaction.domain.Transaction

import scala.concurrent.Future

trait TransactionsSearchable {
  def searchTransactions( accountId: AccountId ): Future[ Seq[ Transaction ] ]
}
