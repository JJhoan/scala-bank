package com.bank.mooc.account.infrastructure.marshaller

import com.bank.mooc.account.domain.AccountId
import spray.json.{ DefaultJsonProtocol, JsValue, JsonFormat, enrichAny }

@Deprecated
object AccountIdJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object UserIdMarshaller extends JsonFormat[AccountId] {
    override def write(value: AccountId): JsValue = value.value.toJson

    override def read(value: JsValue): AccountId = AccountId(value.toString().toInt)
  }
}
