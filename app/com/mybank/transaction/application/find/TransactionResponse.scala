package com.mybank.transaction.application.find

import com.mybank.shared.domain.bus.query.Response
import com.mybank.transaction.domain.Transaction

final case class TransactionResponse( accountId: Int, amount: BigDecimal, description: String ) extends Response

object TransactionResponse {
  def apply: Transaction => TransactionResponse = transaction =>
    TransactionResponse(transaction.accountId.value, transaction.amount.value, transaction.description.toString)
}
