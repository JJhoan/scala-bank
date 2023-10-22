package com.bank.mooc.account.application

import com.bank.mooc.account.domain.Account

import scala.concurrent.Future


trait AccountSearchable {

  def searchAccount(accountId: Int): Future[Option[Account]]

}
