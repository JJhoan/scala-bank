package com.bank.mooc.transaction.application.find

import com.bank.mooc.account.domain.AccountId
import com.bank.shared.domain.bus.query.QueryHandler

import scala.concurrent.Future

@Singleton
final class TransactionFinderQueryHandler( implicit transactionFinder: TransactionFinder ) extends QueryHandler[ TransactionsFinderQuery, TransactionsResponse ] {
  
  override def handle( query: TransactionsFinderQuery ): Future[ TransactionsResponse ] = {
    transactionFinder.find( AccountId( query.accountId ) )
  }
}
