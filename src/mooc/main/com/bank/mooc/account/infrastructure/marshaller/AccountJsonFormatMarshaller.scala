package com.bank.mooc.account.infrastructure.marshaller

import com.bank.mooc.account.domain.{ Account, AccountAmount, AccountId, AccountNumber }
import com.bank.mooc.account.infrastructure.marshaller.AccountIdJsonFormatMarshaller._
import com.bank.mooc.account.infrastructure.marshaller.AccountNumberJsonFormatMarshaller._
import com.bank.mooc.account.infrastructure.marshaller.AccountAmountJsonFormatMarshaller._
import spray.json.{ DefaultJsonProtocol, RootJsonFormat }

@Deprecated
object AccountJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit val accountFormat: RootJsonFormat[ Account ] = jsonFormat(
    Account.apply( _: AccountId, _: AccountNumber, _: AccountAmount ),
    "id", "number", "amount"
  )
}
