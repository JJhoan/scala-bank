package com.mybank.shared.domain.bus.command

import scala.concurrent.Future

trait CommandHandler[T <: Command] {
  def handle(command: T): Future[Unit]
}
