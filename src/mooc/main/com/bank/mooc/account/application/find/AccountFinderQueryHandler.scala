package com.bank.mooc.account.application.find

import com.bank.mooc.account.application.AccountResponse
import com.bank.mooc.account.domain.AccountId
import com.bank.shared.domain.bus.query.QueryHandler

import scala.concurrent.Future

final class AccountFinderQueryHandler( finder: AccountFinder ) extends QueryHandler[ AccountFinderQuery, AccountResponse ] {
  
  override def handle( query: AccountFinderQuery ): Future[ AccountResponse ] = {
    finder.execute( AccountId( query.accountId ) )
  }
}
