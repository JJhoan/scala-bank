package com.mybank.tax.application

import com.mybank.account.domain.AccountAmount
import com.mybank.shared.domain.bus.query.Response
import com.mybank.tax.domain.TaxAmount

final case class TaxResponse( amount: TaxAmount ) extends Response

object TaxResponse {
  def apply( amount: AccountAmount ): TaxResponse = {
    new TaxResponse( TaxAmount( amount.value ) )
  }
  
  def apply( amount: BigDecimal ): TaxResponse = {
    new TaxResponse( TaxAmount( amount ) )
  }
}
