package com.bank.mooc.account.application.find

import com.bank.shared.domain.bus.query.Query

case class AccountFinderQuery(accountId: Int) extends Query
