package com.bank.mooc.account.domain

import com.bank.mooc.account.domain.TransactionDescription.TransactionDescription

import scala.concurrent.Future

final class MoneyWithdrawal( repository: AccountRepository ) {
  
  def withdraw( account: Account, amount: AccountAmount, description: TransactionDescription ): Future[ Unit ] = {
    account.withdraw( amount, description )
    repository.update( account )
  }
  
}
