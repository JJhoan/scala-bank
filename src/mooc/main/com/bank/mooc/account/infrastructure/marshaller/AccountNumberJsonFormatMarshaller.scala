package com.bank.mooc.account.infrastructure.marshaller

import com.bank.mooc.account.domain.AccountNumber
import spray.json.{ DefaultJsonProtocol, DeserializationException, JsString, JsValue, JsonFormat }

@Deprecated
object AccountNumberJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object AccountNumberMarshaller extends JsonFormat[AccountNumber] {
    override def write(value: AccountNumber): JsValue = JsString(value.value)

    override def read(value: JsValue): AccountNumber = value match {
      case JsString(name) => AccountNumber(name)
      case _              => throw DeserializationException("Expected 1 string for AccountNumber")
    }
  }
}
