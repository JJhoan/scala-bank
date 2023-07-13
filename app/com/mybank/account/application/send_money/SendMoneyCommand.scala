package com.mybank.account.application.send_money

import com.mybank.shared.domain.bus.command.Command

case class SendMoneyCommand(from: Int, to: Int, amount: BigDecimal) extends Command
