package com.mybank.shared.infrastructure.bus.command

import com.mybank.shared.domain.bus.command.Command

final class CommandNotRegisteredError( command: Class[_ <: Command] )
  extends Exception( s"The command <${ command.toString }> hasn't a command handler associated" )

