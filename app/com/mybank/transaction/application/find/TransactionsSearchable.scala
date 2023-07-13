package com.mybank.transaction.application.find

import com.mybank.account.domain.AccountId
import com.mybank.transaction.domain.Transaction

import scala.concurrent.Future

trait TransactionsSearchable {
  def searchTransactions( accountId: AccountId ): Future[ Seq[ Transaction ] ]
}
