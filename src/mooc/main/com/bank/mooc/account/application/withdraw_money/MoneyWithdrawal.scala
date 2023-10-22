package com.bank.mooc.account.application.withdraw_money

import com.bank.mooc.account.application.AccountResponse
import com.bank.mooc.account.application.find.AccountFinderQuery
import com.bank.mooc.account.domain.TransactionDescription.TransactionDescription
import com.bank.mooc.account.domain.{ Account, AccountAmount, AccountId, AccountRepository, MoneyWithdrawal => DomainWithdrawMoney }
import com.bank.shared.domain.bus.query.QueryBus
import com.bank.shared.domain.event.EventBus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

final class MoneyWithdrawal ( queryBus: QueryBus, eventBus: EventBus, repository: AccountRepository ) {
  
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
