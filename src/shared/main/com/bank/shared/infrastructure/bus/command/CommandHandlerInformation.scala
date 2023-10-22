package com.bank.shared.infrastructure.bus.command

import com.bank.shared.domain.bus.command.{ Command, CommandHandler }
import org.reflections.Reflections

import java.lang.reflect.ParameterizedType
import scala.jdk.CollectionConverters.CollectionHasAsScala

final class CommandHandlerInformation {

  private val indexedCommandHandlers: Map[Class[_ <: Command], Class[_ <: CommandHandler[_ <: Command]]] = {
    val reflections = new Reflections( "com.mybank" )
    val classes: Set[Class[_ <: CommandHandler[Command]]] = reflections.getSubTypesOf( classOf[ CommandHandler[ Command ]] ).asScala.toSet

    classes.map( handler => {
      val paramType: ParameterizedType = handler.getGenericInterfaces.toList.head.asInstanceOf[ParameterizedType]
      val commandClass = paramType.getActualTypeArguments.toList.head.asInstanceOf[Class[Command]]
      commandClass -> handler
    } ).toMap

  }

  def search( commandClass: Class[_ <: Command] ): Class[_ <: CommandHandler[_ <: Command]] = {
    val commandHandlerClass = indexedCommandHandlers.get( commandClass )

    if ( commandHandlerClass.isEmpty ) {
      throw new CommandNotRegisteredError( commandClass )
    }

    commandHandlerClass.get
  }

}


