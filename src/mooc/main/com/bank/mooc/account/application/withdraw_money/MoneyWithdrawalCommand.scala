package com.bank.mooc.account.application.withdraw_money

import com.bank.shared.domain.bus.command.Command

case class MoneyWithdrawalCommand( accountId: Int, amount: BigDecimal, description: String ) extends Command
