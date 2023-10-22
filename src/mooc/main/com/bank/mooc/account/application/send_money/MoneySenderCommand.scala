package com.bank.mooc.account.application.send_money

import com.bank.shared.domain.bus.command.Command

case class MoneySenderCommand(from: Int, to: Int, amount: BigDecimal) extends Command
