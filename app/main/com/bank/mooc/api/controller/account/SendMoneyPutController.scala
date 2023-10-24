package com.bank.mooc.api.controller.account

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.NoContent
import akka.http.scaladsl.server.{ Directives, StandardRoute }
import com.bank.mooc.account.application.send_money.MoneySenderCommand
import com.bank.mooc.api.controller.account.SendMoneyPutController.Call
import com.bank.shared.domain.bus.command.CommandBus
import spray.json.{ DefaultJsonProtocol, RootJsonFormat }

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

final class SendMoneyPutController (  commandBus: CommandBus )  extends SprayJsonSupport with DefaultJsonProtocol {

  def put( call: Call ): Future[ StandardRoute ] = {
    for {
      _ <- commandBus.dispatch( MoneySenderCommand( call.from, call.to, call.money ) )
    } yield Directives.complete( HttpResponse( NoContent))
  }

}

object SendMoneyPutController extends DefaultJsonProtocol {
  case class Call( from: Int, to: Int, money: BigDecimal )
  
  implicit val accountFormat: RootJsonFormat[ Call ] = jsonFormat(
    Call.apply( _: Int, _: Int, _: BigDecimal ),
    "from", "to", "money"
  )
}