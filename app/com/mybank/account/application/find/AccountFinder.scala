package com.mybank.account.application.find

import com.google.inject.Inject
import com.mybank.account.application.AccountResponse
import com.mybank.account.domain.{ AccountId, AccountRepository, AccountFinder => DomainAccountFinder }
import com.mybank.shared.domain.Singleton

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
final class AccountFinder @Inject( )( repository: AccountRepository ) {
  
  private val finder = new DomainAccountFinder( repository )
  
  def execute( id: AccountId ): Future[ AccountResponse ] = {
    for {
      account <- finder.find( id )
    } yield AccountResponse( account )
  }
  
}
