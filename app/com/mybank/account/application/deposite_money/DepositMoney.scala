package com.mybank.account.application.deposite_money

import com.google.inject.Inject
import com.mybank.account.application.find.AccountFinderQuery
import com.mybank.account.application.{ AccountResponse, AccountUpdatable }
import com.mybank.account.domain.{ Account, AccountAmount, AccountId }
import com.mybank.shared.domain.Singleton
import com.mybank.shared.domain.bus.query.QueryBus
import com.mybank.shared.infrastructure.bus.event.EventBus
import com.mybank.transaction.domain.TransactionDescription.TransactionDescription

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
final class DepositMoney @Inject( )( queryBus: QueryBus, accountUpdatable: AccountUpdatable, eventBus: EventBus ) {
  
  def execute( accountId: AccountId, amount: AccountAmount, description: TransactionDescription ): Future[ Unit ] = {
    for {
      accountResponse <- queryBus.ask[ AccountResponse ]( AccountFinderQuery( accountId.value ) )
      account = Account( accountResponse.id, accountResponse.number, accountResponse.amount )
      _ <- accountUpdatable.updateAccount( account.deposit( amount, description ) )
      _ <- eventBus.publish( account.pullDomainEvents )
    } yield ()
  }
  
}
