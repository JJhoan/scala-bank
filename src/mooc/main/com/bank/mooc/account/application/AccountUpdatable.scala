package com.bank.mooc.account.application

import com.bank.mooc.account.domain.Account

import scala.concurrent.Future

trait AccountUpdatable {
  def updateAccount(account: Account): Future[Unit]
}
