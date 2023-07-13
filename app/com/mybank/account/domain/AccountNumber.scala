package com.mybank.account.domain

import com.mybank.shared.domain.ObjectValue

case class AccountNumber(value: String) extends ObjectValue {
  override def toString: String = value
}
