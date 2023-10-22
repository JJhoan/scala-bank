package com.bank.mooc.transaction.application.create

import com.mybank.transaction.domain.Transaction

import scala.concurrent.Future

trait TransactionCreatable {
  
  def createTransaction(transaction: Transaction): Future[Unit]
  
}
