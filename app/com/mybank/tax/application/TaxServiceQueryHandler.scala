package com.mybank.tax.application

import com.mybank.account.domain.AccountAmount
import com.mybank.shared.domain.Singleton
import com.mybank.shared.domain.bus.query.QueryHandler

import javax.inject.Inject
import scala.concurrent.Future

@Singleton
final class TaxServiceQueryHandler @Inject() (taxService: TaxService) extends QueryHandler[ TaxServiceQuery, TaxResponse ] {
  override def handle( query: TaxServiceQuery ): Future[TaxResponse] = {
    taxService.execute(AccountAmount(query.amount))
  }
}
