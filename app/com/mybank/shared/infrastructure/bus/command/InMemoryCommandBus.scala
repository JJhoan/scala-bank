package com.mybank.shared.infrastructure.bus.command

import com.google.inject.{ Inject, Singleton }
import com.mybank.shared.domain.bus.command.{ Command, CommandBus, CommandHandler }
import play.api.Application

import scala.concurrent.Future

@Singleton
final class InMemoryCommandBus @Inject( )( information: CommandHandlerInformation, app: Application ) extends CommandBus {

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


