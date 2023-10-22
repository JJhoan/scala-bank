package com.bank.mooc.account.application.find

import com.bank.mooc.account.application.AccountResponse
import com.bank.mooc.account.domain.{ AccountId, AccountRepository, AccountFinder => DomainAccountFinder }

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

final class AccountFinder( repository: AccountRepository ) {
  
  private val finder = new DomainAccountFinder( repository )
  
  def execute( id: AccountId ): Future[ AccountResponse ] = {
    for {
      account <- finder.find( id )
    } yield AccountResponse( account )
  }
  
}
