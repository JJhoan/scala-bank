package com.mybank.account.application.withdraw_money

import com.google.inject.Inject
import com.mybank.account.application.AccountResponse
import com.mybank.account.application.find.AccountFinderQuery
import com.mybank.account.domain.{ Account, AccountAmount, AccountId, AccountRepository, MoneyWithdrawal => DomainWithdrawMoney }
import com.mybank.shared.domain.Singleton
import com.mybank.shared.domain.bus.query.QueryBus
import com.mybank.shared.domain.event.EventBus
import com.mybank.transaction.domain.TransactionDescription.TransactionDescription

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
final class MoneyWithdrawal @Inject( )( queryBus: QueryBus, eventBus: EventBus, repository: AccountRepository ) {
  
  private val withdrawMoney = new DomainWithdrawMoney( repository )
  
  def execute( accountId: AccountId, amount: AccountAmount, description: TransactionDescription ): Future[ Unit ] = {
    for {
      accountResponse <- queryBus.ask[ AccountResponse ]( AccountFinderQuery( accountId.value ) )
      account = Account( accountResponse.id, accountResponse.number, accountResponse.amount )
      _ <- withdrawMoney.withdraw( account, amount, description )
      _ <- eventBus.publish( account.pullDomainEvents )
    } yield ()
    
  }
  
}
