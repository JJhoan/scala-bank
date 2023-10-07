package com.mybank.account.domain

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

final class AccountFinder( repository: AccountRepository ) {
  
  def find( accountId: AccountId ): Future[ Account ] = {
    for {
      account <- repository.search( accountId )
      _ = account.getOrElse( throw AccountNotExist( accountId.toString ) )
    } yield account.get
  }
}
