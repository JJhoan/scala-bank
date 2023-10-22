package com.bank.mooc.account.domain

case class AccountNotExist(message: String, cause: Throwable = null) extends Exception(message, cause)
