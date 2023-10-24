package com.bank.mooc.account.infrastructure.marshaller

import com.bank.mooc.account.domain.AccountAmount
import spray.json.{ DefaultJsonProtocol, DeserializationException, JsNumber, JsString, JsValue, JsonFormat }

@Deprecated
object AccountAmountJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object AccountAmountMarshaller extends JsonFormat[AccountAmount] {
    override def write(value: AccountAmount): JsValue = JsString(value.value.toString())

    override def read(value: JsValue): AccountAmount = value match {
      case JsNumber(amount) => AccountAmount( amount )
      case _              => throw DeserializationException("Expected 1 string for AccountNumber")
    }
  }
}
