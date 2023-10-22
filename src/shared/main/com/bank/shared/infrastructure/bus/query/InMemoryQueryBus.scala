package com.bank.shared.infrastructure.bus.query

import com.bank.shared.domain.bus.query.{ Query, QueryBus, QueryHandler, Response }
import com.softwaremill.macwire.MacwireMacros

import scala.concurrent.Future

final class InMemoryQueryBus ( information: QueryHandlerInformation ) extends QueryBus {
  override def ask[ R <: Response ]( query: Query ): Future[ R ] = {
    try {
      val commandHandlerClass: Class[ _ <: QueryHandler[ _ <: Query, _ <: Response ] ] = information
        .search( query.getClass )
      val constructor = commandHandlerClass.decl( termNames.CONSTRUCTOR ).asMethod
      val instancias = mirror.reflectClass( classSymbol ).reflectConstructor( constructor ).apply( )
      val handler: QueryHandler[ Query, R ] = reflect.runtime.currentMirror.classSymbol( commandHandlerClass.getClass)
                                                     .asInstanceOf[ QueryHandler[ Query, R ] ]
      
      handler.handle( query )
    } catch {
      case error: Throwable => throw new QueryHandlerExecutionError( error )
    }
  }
  
}
