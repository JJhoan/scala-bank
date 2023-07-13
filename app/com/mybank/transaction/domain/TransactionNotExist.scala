package com.mybank.transaction.domain

case class TransactionNotExist(message: String, cause: Throwable = null) extends Exception(message, cause)
