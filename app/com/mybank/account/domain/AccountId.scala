package com.mybank.account.domain

case class AccountId(value: Int) {
  override def toString: String = value.toString
}

