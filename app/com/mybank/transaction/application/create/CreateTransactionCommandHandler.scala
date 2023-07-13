package com.mybank.transaction.application.create

import com.google.inject.Inject
import com.mybank.account.domain.AccountId
import com.mybank.shared.domain.Singleton
import com.mybank.shared.domain.bus.command.CommandHandler
import com.mybank.transaction.domain.{ TransactionAmount, TransactionDescription }

import scala.concurrent.Future

@Singleton
final class CreateTransactionCommandHandler @Inject( )( createTransaction: CreateTransaction )
  extends CommandHandler[ CreateTransactionCommand ] {
  
  override def handle( command: CreateTransactionCommand ): Future[Unit] = {
    val accountId              = AccountId( command.accountId )
    val transactionAmount      = TransactionAmount( command.amount )
    val transactionDescription = TransactionDescription.withName( command.description )
    createTransaction.create( accountId, transactionAmount, transactionDescription )
  }
  
}
