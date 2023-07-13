package com.mybank.account.application.send_money

import com.google.inject.Inject
import com.mybank.account.domain.{ AccountAmount, AccountId }
import com.mybank.shared.domain.Singleton
import com.mybank.shared.domain.bus.command.CommandHandler

import scala.concurrent.Future

@Singleton
private class SendMoneyCommandHandler @Inject()(sendMoney: SendMoney) extends CommandHandler[SendMoneyCommand] {
  override def handle(command: SendMoneyCommand): Future[Unit] = {
    sendMoney.execute( AccountId(command.from), AccountId(command.to), AccountAmount(command.amount) )
  }
}
