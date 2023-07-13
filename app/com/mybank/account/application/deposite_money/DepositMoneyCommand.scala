package com.mybank.account.application.deposite_money

import com.mybank.shared.domain.bus.command.Command

final case class DepositMoneyCommand( accountId: Int, amount: BigDecimal, descrition: String ) extends Command
