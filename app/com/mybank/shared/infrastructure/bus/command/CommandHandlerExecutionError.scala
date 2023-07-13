package com.mybank.shared.infrastructure.bus.command

final class CommandHandlerExecutionError( cause: Throwable ) extends RuntimeException( cause )

object CommandHandlerExecutionError {
  def unapply(error: CommandHandlerExecutionError): String = error.getMessage
}
