package com.mybank.account.application

import com.mybank.account.domain.Account


trait AccountCreatable {

  def createAccount(account: Account): Unit

}
