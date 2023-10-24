package com.bank.mooc.account.application.send_money

import com.bank.mooc.account.domain.{ AccountAmount, AccountId }
import com.bank.shared.domain.bus.command.CommandHandler

import scala.concurrent.Future

final class MoneySenderCommandHandler (implicit sendMoney: MoneySender) extends CommandHandler[MoneySenderCommand] {
  override def handle(command: MoneySenderCommand): Future[Unit] = {
    sendMoney.execute( AccountId( command.from), AccountId( command.to), AccountAmount( command.amount) )
  }
}
