package com.mybank.shared.domain.bus.command

final class CommandHandlerExecutionError(message: String, cause: Throwable = null) extends Exception(message, cause)
