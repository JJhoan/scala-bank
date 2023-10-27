package com.bank.mooc.transaction.application.find

import com.bank.mooc.transaction.domain.Transaction
import com.bank.shared.domain.bus.query.Response

final case class TransactionResponse( accountId: Int, amount: BigDecimal, description: String ) extends Response

object TransactionResponse {
  def apply( transaction: Transaction ): TransactionResponse = {
    TransactionResponse( transaction.accountId.value, transaction.amount.value, transaction.description )
  }
}
