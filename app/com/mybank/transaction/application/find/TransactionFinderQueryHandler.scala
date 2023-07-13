package com.mybank.transaction.application.find

import com.google.inject.Inject
import com.mybank.account.domain.AccountId
import com.mybank.shared.domain.Singleton
import com.mybank.shared.domain.bus.query.QueryHandler

import scala.concurrent.Future

@Singleton
final class TransactionFinderQueryHandler @Inject( )( transactionFinder: TransactionFinder ) extends QueryHandler[ TransactionsFinderQuery, TransactionsResponse ] {
  
  override def handle( query: TransactionsFinderQuery ): Future[TransactionsResponse] = {
    transactionFinder.find( AccountId( query.accountId ) )
  }
}
