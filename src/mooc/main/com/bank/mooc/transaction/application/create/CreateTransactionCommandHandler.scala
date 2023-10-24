package com.bank.mooc.transaction.application.create

import com.bank.mooc.account.domain.AccountId
import com.bank.mooc.transaction.domain.{ TransactionAmount, TransactionDescription }
import com.bank.shared.domain.bus.command.CommandHandler

import scala.concurrent.Future

final class CreateTransactionCommandHandler( implicit createTransaction: CreateTransaction )
  extends CommandHandler[ CreateTransactionCommand ] {
  
  override def handle( command: CreateTransactionCommand ): Future[ Unit ] = {
    val accountId              = AccountId( command.accountId )
    val transactionAmount      = TransactionAmount( command.amount )
    val transactionDescription = TransactionDescription.withName( command.description )
    createTransaction.create( accountId, transactionAmount, transactionDescription )
  }
  
}
