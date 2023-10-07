package com.mybank.account.application.withdraw_money

import com.google.inject.Inject
import com.mybank.account.domain.{ AccountAmount, AccountId }
import com.mybank.shared.domain.Singleton
import com.mybank.shared.domain.bus.command.CommandHandler
import com.mybank.transaction.domain.TransactionDescription

import scala.concurrent.Future

@Singleton
class MoneyWithdrawalCommandHandler @Inject( )( withdrawMoney: MoneyWithdrawal ) extends CommandHandler[ MoneyWithdrawalCommand ] {
  override def handle( command: MoneyWithdrawalCommand ): Future[Unit] = {
    val accountId     = AccountId( command.accountId )
    val accountAmount = AccountAmount( command.amount )
    val description   = TransactionDescription.withName( command.description )
    
    withdrawMoney.execute( accountId, accountAmount, description )
  }
}
