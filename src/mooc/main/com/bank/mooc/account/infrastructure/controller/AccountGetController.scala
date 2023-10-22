package com.bank.mooc.account.infrastructure.controller

import com.bank.shared.domain.bus.query.QueryBus
import com.bank.shared.infrastructure.controller.ApiController
import com.google.inject.{ Inject, Singleton }
import com.mybank.account.application.AccountResponse
import com.mybank.account.application.find.AccountFinderQuery
import com.mybank.shared.domain.bus.query.QueryBus
import com.mybank.shared.infrastructure.controller.ApiController

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

final class AccountGetController ( cc: ControllerComponents, queryBus: QueryBus ) extends ApiController( cc ) {

  def index( accountId: Int ): Action[AnyContent] = cc.actionBuilder.async {
    for {
      account <- queryBus.ask[AccountResponse]( AccountFinderQuery( accountId ) )
    } yield Ok( findAccountResponse( account.id, account.number, account.amount ) )
  }

  case class findAccountResponse( id: Int, number: String, amount: BigDecimal )
  implicit val requestJson: OWrites[findAccountResponse] = Json.writes[findAccountResponse]

}
