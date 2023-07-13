package com.mybank.transaction.domain

import java.util.UUID

final case class TransactionId(value: UUID = UUID.randomUUID())
