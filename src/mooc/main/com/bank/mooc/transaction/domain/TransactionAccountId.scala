package com.bank.mooc.transaction.domain

case class TransactionAccountId(value: Int) {
  override def toString: String = value.toString
}

