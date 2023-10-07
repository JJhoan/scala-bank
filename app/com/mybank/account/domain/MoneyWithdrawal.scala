package com.mybank.account.domain

import com.mybank.transaction.domain.TransactionDescription.TransactionDescription

import scala.concurrent.Future

final class MoneyWithdrawal( repository: AccountRepository ) {
  
  def withdraw( account: Account, amount: AccountAmount, description: TransactionDescription ): Future[ Unit ] = {
    account.withdraw( amount, description )
    repository.update( account )
  }
  
}
