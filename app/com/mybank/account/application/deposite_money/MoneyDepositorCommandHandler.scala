package com.mybank.account.application.deposite_money

import com.google.inject.Inject
import com.mybank.account.domain.{ AccountAmount, AccountId }
import com.mybank.shared.domain.Singleton
import com.mybank.shared.domain.bus.command.CommandHandler
import com.mybank.transaction.domain.TransactionDescription

import scala.concurrent.Future

@Singleton
final class MoneyDepositorCommandHandler @Inject( )( depositMoney: MoneyDepositor ) extends CommandHandler[ MoneyDepositorCommand ] {
  
  override def handle( command: MoneyDepositorCommand ): Future[ Unit ] = {
    val accountId              = AccountId( command.accountId )
    val accountAmount          = AccountAmount( command.amount )
    val transactionDescription = TransactionDescription.withName( command.description )
    
    depositMoney.execute( accountId, accountAmount, transactionDescription )
  }
}
