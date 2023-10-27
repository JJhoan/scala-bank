package com.bank.mooc.transaction.application.find

import com.bank.mooc.transaction.domain.Transaction
import com.bank.shared.domain.bus.query.Response

case class TransactionsResponse( transactions: Seq[ TransactionResponse ] ) extends Response

object TransactionsResponse {
  def from( transactions: Seq[ Transaction ] ): TransactionsResponse = {
    new TransactionsResponse( transactions.map( TransactionResponse.apply ) )
  }
}
