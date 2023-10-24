package com.bank.mooc.api.controller.account

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.bank.mooc.account.application.AccountResponse
import com.bank.mooc.account.application.find.AccountFinderQuery
import com.bank.shared.domain.bus.query.QueryBus
import spray.json.{ DefaultJsonProtocol, RootJsonFormat }

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

final class AccountGetController( queryBus: QueryBus ) extends SprayJsonSupport with DefaultJsonProtocol {
  
  case class FindAccountResponse( id: Int, number: String, amount: BigDecimal )
  
  def get( accountId: Int ): Future[ FindAccountResponse ] = {
    for {
      account <- queryBus.ask[ AccountResponse ]( AccountFinderQuery( accountId ) )
    } yield FindAccountResponse( account.id, account.number, account.amount )
  }
  
  implicit val accountFormat: RootJsonFormat[ FindAccountResponse ] = jsonFormat(
    FindAccountResponse.apply( _: Int, _: String, _: BigDecimal ),
    "id", "number", "amount"
  )
  
}
