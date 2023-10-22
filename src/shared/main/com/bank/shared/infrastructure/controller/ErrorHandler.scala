package com.bank.shared.infrastructure.controller

import com.mybank.shared.infrastructure.bus.command.CommandHandlerExecutionError
import com.mybank.shared.infrastructure.bus.command.CommandHandlerExecutionError.unapply
import play.api.http.{ HttpErrorHandler, Writeable }
import play.api.libs.json.{ Json, OWrites }
import play.api.mvc.Results._
import play.api.mvc._

import javax.inject._
import scala.concurrent._

@Singleton
class ErrorHandler extends HttpErrorHandler {
  def onClientError( request: RequestHeader, statusCode: Int, message: String ): Future[Result] = {
    Future.successful(
      Status( statusCode )( "A client error occurred: " + message )
      )
  }

  implicit def jsonWriteable[T]( implicit writes: OWrites[T] ): Writeable[T] =
    Writeable.writeableOf_JsValue.map[T]( Json.toJson( _ ) )

  def onServerError( request: RequestHeader, exception: Throwable ): Future[Result] = {
    exception match {
      case error: CommandHandlerExecutionError => Future.successful(
        BadRequest( unapply( error ) )
        )
      case _ => Future.successful( InternalServerError( "A server error occurred: " + exception.getMessage ) )
    }
  }
}
