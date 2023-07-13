package com.mybank.account.application.deposite_money

import com.google.inject.Inject
import com.mybank.account.domain.{ AccountAmount, AccountId }
import com.mybank.shared.domain.Singleton
import com.mybank.shared.domain.bus.command.CommandHandler
import com.mybank.transaction.domain.TransactionDescription

import scala.concurrent.Future

@Singleton
final class DepositMoneyCommandHandler @Inject( )( depositMoney: DepositMoney ) extends CommandHandler[ DepositMoneyCommand ] {
  
  override def handle( command: DepositMoneyCommand ): Future[ Unit ] = {
    val accountId              = AccountId( command.accountId )
    val accountAmount          = AccountAmount( command.amount )
    val transactionDescription = TransactionDescription.withName( command.descrition )
    
    depositMoney.execute( accountId, accountAmount, transactionDescription )
  }
}
