package com.mybank.account.application.withdraw_money

import com.mybank.shared.domain.bus.command.Command

case class WithdrawMoneyCommand( accountId: Int, amount: BigDecimal, description: String ) extends Command
