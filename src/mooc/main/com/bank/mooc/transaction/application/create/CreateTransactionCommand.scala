package com.bank.mooc.transaction.application.create

import com.bank.shared.domain.bus.command.Command

final case class CreateTransactionCommand( accountId: Int, amount: BigDecimal, description: String ) extends Command
