package com.mybank.tax.domain

import com.mybank.account.domain.AccountAmount

final case class TaxAmount( value: BigDecimal ) {
  
  def *( amount: AccountAmount ): TaxAmount = TaxAmount( value * amount.value )
}
