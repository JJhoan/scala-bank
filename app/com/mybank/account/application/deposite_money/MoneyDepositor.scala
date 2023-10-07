package com.mybank.account.application.deposite_money

import com.google.inject.Inject
import com.mybank.account.domain.{ AccountAmount, AccountFinder, AccountId, AccountRepository, MoneyDepositor => DomainMoneyDepositor }
import com.mybank.shared.domain.Singleton
import com.mybank.shared.domain.event.EventBus
import com.mybank.transaction.domain.TransactionDescription.TransactionDescription

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
final class MoneyDepositor @Inject( )( eventBus: EventBus, repository: AccountRepository ) {
  
  private val finder         = new AccountFinder( repository )
  private val moneyDepositor = new DomainMoneyDepositor( repository )
  
  def execute( accountId: AccountId, amount: AccountAmount, description: TransactionDescription ): Future[ Unit ] = {
    for {
      account <- finder.find( accountId )
      _ <- moneyDepositor.deposit( account, amount, description )
      _ <- eventBus.publish( account.pullDomainEvents )
    } yield ()
  }
  
}
