package com.mybank.account.domain

case class AccountNotExist(message: String, cause: Throwable = null) extends Exception(message, cause)
