package com.bank.mooc.account.application

import com.bank.mooc.account.domain.Account


trait AccountCreatable {

  def createAccount(account: Account): Unit

}
