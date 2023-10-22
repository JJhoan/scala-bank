package com.mybank.account.infrastructure.controller

import com.mybank.account.application.send_money.MoneySenderCommand
import com.mybank.shared.domain.bus.command.CommandBus
import com.mybank.shared.infrastructure.controller.ApiController
import play.api.libs.json._
import play.api.mvc._

import javax.inject._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class SendMoneyPutController @Inject( )( cc: ControllerComponents, commandBus: CommandBus ) extends ApiController( cc ) {

  def index( ): Action[Call] = Action.async( parse.json[Call] ) { implicit request: Request[Call] =>
    val body = request.body
    for {
      _ <- commandBus.dispatch( MoneySenderCommand( body.from, body.to, body.money ) )
    } yield Accepted
  }

  case class Call( from: Int, to: Int, money: BigDecimal )
  implicit val requestJson: OFormat[Call] = Json.format[Call]
}