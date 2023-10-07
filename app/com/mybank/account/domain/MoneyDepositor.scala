package com.mybank.account.domain

import com.mybank.transaction.domain.TransactionDescription.TransactionDescription

import scala.concurrent.Future

final class MoneyDepositor( repository: AccountRepository ) {
  
  def deposit( account: Account, amount: AccountAmount, description: TransactionDescription ): Future[ Unit ] = {
    account.deposit( amount, description )
    repository.update( account )
  }
}


