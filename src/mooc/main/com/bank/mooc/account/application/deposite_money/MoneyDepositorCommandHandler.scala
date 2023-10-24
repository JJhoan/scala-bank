package com.bank.mooc.account.application.deposite_money


import com.bank.mooc.account.domain.{ AccountAmount, AccountId, TransactionDescription }
import com.bank.shared.domain.bus.command.CommandHandler

import scala.concurrent.Future

final class MoneyDepositorCommandHandler ( implicit depositMoney: MoneyDepositor ) extends CommandHandler[ MoneyDepositorCommand ] {
  
  override def handle( command: MoneyDepositorCommand ): Future[ Unit ] = {
    val accountId              = AccountId( command.accountId )
    val accountAmount          = AccountAmount( command.amount )
    val transactionDescription = TransactionDescription.withName( command.description )
    
    depositMoney.execute( accountId, accountAmount, transactionDescription )
  }
}
