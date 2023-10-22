package com.bank.mooc.account.application.deposite_money

import com.bank.mooc.account.domain.TransactionDescription.TransactionDescription
import com.bank.mooc.account.domain.{ AccountAmount, AccountFinder, AccountId, AccountRepository, MoneyDepositor => DomainMoneyDepositor }
import com.bank.shared.domain.event.EventBus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

final class MoneyDepositor( eventBus: EventBus, repository: AccountRepository ) {
  def execute( accountId: AccountId, amount: AccountAmount, description: TransactionDescription ): Future[ Unit ] = {
    for {
      account <- finder.find( accountId )
      _ <- moneyDepositor.deposit( account, amount, description )
      _ <- eventBus.publish( account.pullDomainEvents )
    } yield ()
  }
  
  private val finder = new AccountFinder( repository )
  
  private val moneyDepositor = new DomainMoneyDepositor( repository )
  
}
