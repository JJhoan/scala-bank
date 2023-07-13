package com.mybank.account.application.tax_amount

import com.google.inject.Inject
import com.mybank.account.domain.AccountId
import com.mybank.shared.domain.account.AccountAmountChangedDomain
import com.mybank.shared.domain.{ DomainEventSubscriber, Singleton }
import com.mybank.transaction.application.create.CreateTransaction
import com.mybank.transaction.domain.{ TransactionAmount, TransactionDescription }

@DomainEventSubscriber( Array{
  classOf[ AccountAmountChangedDomain ]
} )
@Singleton
final class CreateTransactionOnAccountAmountChanged @Inject( )( createTransaction: CreateTransaction ) {
  
  def on( event: AccountAmountChangedDomain ): Unit = {
    val accountId     = AccountId( event.aggregateId.toInt )
    val accountAmount = TransactionAmount( event.amount )
    val description   = TransactionDescription.withName( event.description )
    
    createTransaction.create( accountId, accountAmount, description)
    
  }
}
