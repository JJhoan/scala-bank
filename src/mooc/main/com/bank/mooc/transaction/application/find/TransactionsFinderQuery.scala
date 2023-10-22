package com.bank.mooc.transaction.application.find

import com.mybank.shared.domain.bus.query.Query

final case class TransactionsFinderQuery(accountId: Int) extends Query
