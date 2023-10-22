package com.bank.shared.infrastructure.controller

import play.api.http.Writeable
import play.api.libs.json.{ Json, OWrites }
import play.api.mvc.{ AbstractController, ControllerComponents }

abstract class ApiController( cc: ControllerComponents ) extends AbstractController( cc ) {

  implicit def jsonWriteable[T]( implicit writes: OWrites[T] ): Writeable[T] =
    Writeable.writeableOf_JsValue.map[T]( Json.toJson( _ ) )

}
