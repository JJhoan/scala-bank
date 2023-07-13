package com.mybank.transaction.application.create

import com.mybank.shared.domain.bus.command.Command

final case class CreateTransactionCommand(accountId: Int, amount: BigDecimal, description: String) extends Command
