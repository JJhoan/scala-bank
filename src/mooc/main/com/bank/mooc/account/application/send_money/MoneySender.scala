package com.bank.mooc.account.application.send_money

import com.bank.mooc.account.domain.{ AccountAmount, AccountFinder, AccountId, AccountRepository, MoneyDepositor, MoneyWithdrawal, TransactionDescription }
import com.bank.shared.domain.event.EventBus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
final class MoneySender ( repository: AccountRepository, eventBus: EventBus ) {
  
  private val moneyWithdrawal = new MoneyWithdrawal( repository )
  private val moneyDepositor  = new MoneyDepositor( repository )
  private val finder          = new AccountFinder( repository )
  
  def execute( from: AccountId, to: AccountId, amount: AccountAmount ): Future[ Unit ] = {
    for {
      accountFrom <- finder find from
      accountTo <- finder find to
      _ <- moneyWithdrawal.withdraw( accountFrom, amount, TransactionDescription.TransactionTo )
      _ <- moneyDepositor.deposit( accountTo, amount, TransactionDescription.TransactionTo )
      _ <- eventBus publish accountTo.pullDomainEvents
      _ <- eventBus publish accountFrom.pullDomainEvents
    } yield ()
  }
  
}
