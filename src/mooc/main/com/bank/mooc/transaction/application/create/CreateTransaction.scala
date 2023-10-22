package com.bank.mooc.transaction.application.create

import com.google.inject.Inject
import com.mybank.account.domain.AccountId
import com.mybank.shared.domain.Singleton
import com.mybank.transaction.domain.TransactionDescription.TransactionDescription
import com.mybank.transaction.domain.{ Transaction, TransactionAmount, TransactionRepository }

import scala.concurrent.Future

@Singleton
final class CreateTransaction @Inject( )( repository: TransactionRepository ) {
  
  def create( accountId: AccountId, amount: TransactionAmount, description: TransactionDescription ): Future[ Unit ] = {
    val transaction = Transaction( accountId, amount, description )
    repository.createTransaction( transaction )
  }
}