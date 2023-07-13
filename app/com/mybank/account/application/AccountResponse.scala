package com.mybank.account.application

import com.mybank.account.domain.Account
import com.mybank.shared.domain.bus.query.Response

case class AccountResponse( id: Int, number: String, amount: BigDecimal ) extends Response

object AccountResponse {
  def apply: Account => AccountResponse = account =>
    AccountResponse( account.id.value, account.number.value, account.amount.value )
}

