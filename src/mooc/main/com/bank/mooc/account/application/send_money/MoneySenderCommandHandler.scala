package com.bank.mooc.account.application.send_money

import com.bank.mooc.account.domain.{ AccountAmount, AccountId }
import com.bank.shared.domain.bus.command.CommandHandler

import scala.concurrent.Future

@Singleton
private class MoneySenderCommandHandler (sendMoney: MoneySender) extends CommandHandler[MoneySenderCommand] {
  override def handle(command: MoneySenderCommand): Future[Unit] = {
    sendMoney.execute( AccountId( command.from), AccountId( command.to), AccountAmount( command.amount) )
  }
}
