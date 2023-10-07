package com.mybank.account.application.withdraw_money

import com.mybank.shared.domain.bus.command.Command

case class MoneyWithdrawalCommand( accountId: Int, amount: BigDecimal, description: String ) extends Command
