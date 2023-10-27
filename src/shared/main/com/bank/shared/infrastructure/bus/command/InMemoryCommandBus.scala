package com.bank.shared.infrastructure.bus.command

import com.bank.shared.domain.bus.command.{ Command, CommandBus, CommandHandler }
import com.bank.shared.infrastructure.dependency_injection.SharedModuleDependencyContainer

import scala.concurrent.Future

final class InMemoryCommandBus( information: CommandHandlerInformation, sharedModule: SharedModuleDependencyContainer ) extends CommandBus {

  override def dispatch( command: Command ): Future[Unit] = {
    try {
      val commandHandlerClass: Class[_ <: CommandHandler[_ <: Command]] = information.search( command.getClass )

      val handler: CommandHandler[Command] = sharedModule.commands.find(c => c.getClass.isInstance(commandHandlerClass) ).get.asInstanceOf[CommandHandler[Command]]

      handler.handle( command )
    } catch {
      case error : Throwable => throw new CommandHandlerExecutionError( error )
    }
  }

}


