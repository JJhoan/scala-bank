package com.mybank.account.application.withdraw_money

import com.bank.mooc.account.application.withdraw_money.{ MoneyWithdrawal, MoneyWithdrawalCommand }
import com.bank.mooc.account.domain.{ AccountAmount, AccountId, TransactionDescription }
import com.bank.shared.domain.bus.command.CommandHandler

import scala.concurrent.Future

@Singleton
class MoneyWithdrawalCommandHandler ( withdrawMoney: MoneyWithdrawal ) extends CommandHandler[ MoneyWithdrawalCommand ] {
  override def handle( command: MoneyWithdrawalCommand ): Future[Unit] = {
    val accountId     = AccountId( command.accountId )
    val accountAmount = AccountAmount( command.amount )
    val description   = TransactionDescription.withName( command.description )
    
    withdrawMoney.execute( accountId, accountAmount, description )
  }
}
