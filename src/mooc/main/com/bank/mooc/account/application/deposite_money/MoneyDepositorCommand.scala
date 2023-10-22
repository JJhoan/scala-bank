package com.bank.mooc.account.application.deposite_money

import com.bank.shared.domain.bus.command.Command

final case class MoneyDepositorCommand( accountId: Int, amount: BigDecimal, description: String ) extends Command
