package com.bank.shared.infrastructure.bus.command

import com.bank.shared.domain.bus.command.Command

final class CommandNotRegisteredError( command: Class[_ <: Command] )
  extends Exception( s"The command <${ command.toString }> hasn't a command handler associated" )

