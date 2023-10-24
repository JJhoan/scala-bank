package com.bank.mooc.transaction.application.create

import com.bank.mooc.account.domain.AccountId
import com.bank.mooc.transaction.domain.TransactionDescription.TransactionDescription
import com.bank.mooc.transaction.domain.{ Transaction, TransactionAmount, TransactionRepository }

import scala.concurrent.Future

final class CreateTransaction ( repository: TransactionRepository ) {
  
  def create( accountId: AccountId, amount: TransactionAmount, description: TransactionDescription ): Future[ Unit ] = {
    val transaction = Transaction( accountId, amount, description )
    repository.save( transaction )
  }
}
