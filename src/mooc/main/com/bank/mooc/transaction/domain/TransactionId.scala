package com.bank.mooc.transaction.domain

import java.util.UUID

final case class TransactionId(value: UUID = UUID.randomUUID())
