package com.mybank.account.application

import com.mybank.account.domain.Account

import scala.concurrent.Future

trait AccountUpdatable {
  def updateAccount(account: Account): Future[Unit]
}
