package com.mybank.account.application.find

import com.mybank.shared.domain.bus.query.Query

case class AccountFinderQuery(accountId: Int) extends Query
