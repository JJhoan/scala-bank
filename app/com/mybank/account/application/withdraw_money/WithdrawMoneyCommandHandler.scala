package com.mybank.account.application.withdraw_money

import com.google.inject.Inject
import com.mybank.account.domain.{ AccountAmount, AccountId }
import com.mybank.shared.domain.Singleton
import com.mybank.shared.domain.bus.command.CommandHandler
import com.mybank.transaction.domain.TransactionDescription

import scala.concurrent.Future

@Singleton
class WithdrawMoneyCommandHandler @Inject( )( withdrawMoney: WithdrawMoney ) extends CommandHandler[ WithdrawMoneyCommand ] {
  override def handle( command: WithdrawMoneyCommand ): Future[Unit] = {
    val accountId     = AccountId( command.accountId )
    val accountAmount = AccountAmount( command.amount )
    val description   = TransactionDescription.withName( command.description )
    
    withdrawMoney.execute( accountId, accountAmount, description )
  }
}
