package com.mybank.account.infrastructure.controller

import com.google.inject.{ Inject, Singleton }
import com.mybank.account.application.AccountResponse
import com.mybank.account.application.find.AccountFinderQuery
import com.mybank.shared.domain.bus.query.QueryBus
import com.mybank.shared.infrastructure.controller.ApiController
import play.api.libs.json.{ Json, OWrites }
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
final class AccountGetController @Inject( )( cc: ControllerComponents, queryBus: QueryBus ) extends ApiController( cc ) {

  def index( accountId: Int ): Action[AnyContent] = cc.actionBuilder.async {
    for {
      account <- queryBus.ask[AccountResponse]( AccountFinderQuery( accountId ) )
    } yield Ok( findAccountResponse( account.id, account.number, account.amount ) )
  }

  case class findAccountResponse( id: Int, number: String, amount: BigDecimal )
  implicit val requestJson: OWrites[findAccountResponse] = Json.writes[findAccountResponse]

}
