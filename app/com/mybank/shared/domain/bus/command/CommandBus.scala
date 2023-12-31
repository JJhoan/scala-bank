package com.mybank.shared.domain.bus.command

import scala.concurrent.Future

trait CommandBus {
  @throws[CommandHandlerExecutionError]
  def dispatch(command: Command): Future[Unit]
}
