package com.mybank.account.application.find

import com.google.inject.Inject
import com.mybank.account.application.AccountResponse
import com.mybank.account.domain.AccountId
import com.mybank.shared.domain.Singleton
import com.mybank.shared.domain.bus.query.QueryHandler

import scala.concurrent.Future

@Singleton
final class AccountFinderQueryHandler @Inject( )( finder: AccountFinder ) extends QueryHandler[ AccountFinderQuery, AccountResponse ] {
  
  override def handle( query: AccountFinderQuery ): Future[ AccountResponse ] = {
    finder.execute( AccountId( query.accountId ) )
  }
}
