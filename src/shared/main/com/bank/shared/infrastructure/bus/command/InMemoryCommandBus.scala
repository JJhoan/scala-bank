package com.bank.shared.infrastructure.bus.command

import com.bank.shared.domain.bus.command.{ Command, CommandBus, CommandHandler }

import scala.concurrent.Future

final class InMemoryCommandBus( information: CommandHandlerInformation, app: Application ) extends CommandBus {

  override def dispatch( command: Command ): Future[Unit] = {
    try {
      val commandHandlerClass: Class[_ <: CommandHandler[_ <: Command]] = information.search( command.getClass )

      val handler: CommandHandler[Command] = app.injector.instanceOf( commandHandlerClass ).asInstanceOf[CommandHandler[Command]]

      handler.handle( command )
    } catch {
      case error : Throwable => throw new CommandHandlerExecutionError( error )
    }
  }

}


