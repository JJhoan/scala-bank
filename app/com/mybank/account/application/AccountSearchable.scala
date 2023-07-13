package com.mybank.account.application

import com.mybank.account.domain.Account

import scala.concurrent.Future


trait AccountSearchable {

  def searchAccount(accountId: Int): Future[Option[Account]]

}
