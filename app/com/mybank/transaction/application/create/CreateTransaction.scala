package com.mybank.transaction.application.create

import com.google.inject.Inject
import com.mybank.account.domain.AccountId
import com.mybank.shared.domain.Singleton
import com.mybank.transaction.domain.{ Transaction, TransactionAmount }
import com.mybank.transaction.domain.TransactionDescription.TransactionDescription

import scala.concurrent.Future

@Singleton
final class CreateTransaction @Inject()(transactionCreatable: TransactionCreatable) {
  
  def create(accountId: AccountId, amount: TransactionAmount, description: TransactionDescription): Future[Unit] = {
    val transaction = Transaction(accountId, amount, description)
    transactionCreatable.createTransaction(transaction)
  }
}
