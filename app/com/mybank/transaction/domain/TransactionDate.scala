package com.mybank.transaction.domain

import java.time.LocalDateTime

final case class TransactionDate( value: LocalDateTime = LocalDateTime.now() )

