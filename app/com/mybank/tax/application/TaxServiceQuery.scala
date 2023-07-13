package com.mybank.tax.application

import com.mybank.shared.domain.bus.query.Query

final case class TaxServiceQuery(amount: BigDecimal) extends Query
