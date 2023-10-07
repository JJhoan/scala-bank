package com.mybank.account.application.deposite_money

import com.mybank.shared.domain.bus.command.Command

final case class MoneyDepositorCommand( accountId: Int, amount: BigDecimal, description: String ) extends Command
