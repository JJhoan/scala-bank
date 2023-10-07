package com.mybank.account.application.send_money

import com.google.inject.Inject
import com.mybank.account.domain.{ AccountAmount, AccountId }
import com.mybank.shared.domain.Singleton
import com.mybank.shared.domain.bus.command.CommandHandler

import scala.concurrent.Future

@Singleton
private class MoneySenderCommandHandler @Inject()(sendMoney: MoneySender) extends CommandHandler[MoneySenderCommand] {
  override def handle(command: MoneySenderCommand): Future[Unit] = {
    sendMoney.execute( AccountId(command.from), AccountId(command.to), AccountAmount(command.amount) )
  }
}
