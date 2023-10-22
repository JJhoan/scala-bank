package com.bank.mooc.account.domain

import com.bank.shared.domain.ObjectValue


case class AccountNumber(value: String) extends ObjectValue {
  override def toString: String = value
}
