package com.bank.mooc.transaction.application.find

import com.bank.mooc.transaction.domain.Transaction
import com.bank.shared.domain.bus.query.Response

final case class TransactionsResponse( transactions: Seq[ TransactionResponse ] ) extends Response

object TransactionsResponse {
  def apply( transactions: Seq[ Transaction ] ): TransactionsResponse = {
    TransactionsResponse( transactions.map( TransactionResponse( _ ) ) )
  }
}
