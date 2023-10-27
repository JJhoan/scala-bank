package com.bank.mooc.api

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.bank.mooc.api.controller.account.SendMoneyPutController._

import scala.util.{ Failure, Success }

final class Routes( container: EntryPointDependencyContainer ) {
  private val account = get{
    path( "accounts" / IntNumber ){ accountId =>
      onComplete( container.accountGetController.get( accountId ) ){
        case Success( _ ) => complete( "Successful" )
        case Failure( _ ) => complete( "Failed" )
      }
    }
  } ~ path( "accounts/send" ){
    put{
      entity( as[ Call ] ){ call =>
        onComplete( container.sendMoneyPutController.put( call ) ){
          case Success( value ) => complete( s"The result was $value" )
          case Failure( ex ) => complete( StatusCodes.InternalServerError, s"An error occurred: ${ ex.getMessage }" )
        }
      }
    }
  }
  
  //val all: Route = status ~ user ~ video
  val all: Route = account
  
  /*private def jsonBody( handler: Map[ String, JsValue ] => Route ): Route = {
    entity( as[ JsValue ] )( json => handler( json.asJsObject.fields ) )
  }*/
}



