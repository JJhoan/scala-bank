package com.bank.mooc.account.domain

import scala.concurrent.Future

trait AccountRepository {
  def update(account: Account): Future[Unit]
  def search(accountId: AccountId): Future[Option[Account]]
  
  def save(account: Account): Future[Unit]
  def all(): Future[Seq[Account]]
}
