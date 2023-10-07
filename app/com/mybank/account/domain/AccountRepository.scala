package com.mybank.account.domain

import scala.concurrent.Future

trait AccountRepository {
  def update(account: Account): Future[Unit]
  def search(accountId: AccountId): Future[Option[Account]]
}
