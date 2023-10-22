package com.bank.mooc.transaction.domain

import java.time.LocalDateTime

final case class TransactionDate( value: LocalDateTime = LocalDateTime.now() )

