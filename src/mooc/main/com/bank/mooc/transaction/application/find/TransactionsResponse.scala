package com.bank.mooc.transaction.application.find

import com.mybank.shared.domain.bus.query.Response
import com.mybank.transaction.domain.Transaction

final case class TransactionsResponse( transactions: Seq[TransactionResponse] ) extends Response

object TransactionsResponse {
  def apply: Seq[Transaction] => TransactionsResponse = transactions =>
    TransactionsResponse(transactions.map(TransactionResponse(_)))
}
