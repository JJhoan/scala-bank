package com.bank.mooc.transaction.application.find

import com.bank.shared.domain.bus.query.Query

final case class TransactionsFinderQuery(accountId: Int) extends Query
