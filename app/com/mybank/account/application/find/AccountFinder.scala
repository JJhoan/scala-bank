package com.mybank.account.application.find

import com.google.inject.Inject
import com.mybank.account.application.{ AccountResponse, AccountSearchable }
import com.mybank.account.domain.{ AccountId, AccountNotExist }
import com.mybank.shared.domain.Singleton

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
final class AccountFinder @Inject( )( accountSearchable: AccountSearchable ) {
  
  def execute( id: AccountId ): Future[ AccountResponse ] = {
    for {
      accountResponse <- accountSearchable.searchAccount( id.value )
      account = accountResponse.map( AccountResponse( _ ) )
                               .getOrElse( throw AccountNotExist( id.toString ) )
    } yield account
  }
  
}
