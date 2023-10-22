package com.bank.mooc.account.application

import com.bank.mooc.account.domain.Account
import com.bank.shared.domain.bus.query.Response

case class AccountResponse( id: Int, number: String, amount: BigDecimal ) extends Response

object AccountResponse {
  def apply: Account => AccountResponse = account =>
    AccountResponse( account.id.value, account.number.value, account.amount.value )
}

