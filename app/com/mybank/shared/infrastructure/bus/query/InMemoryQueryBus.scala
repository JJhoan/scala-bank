package com.mybank.shared.infrastructure.bus.query

import com.google.inject.{ Inject, Singleton }
import com.mybank.shared.domain.bus.query.{ Query, QueryBus, QueryHandler, Response }
import play.api.Application

import scala.concurrent.Future

@Singleton
final class InMemoryQueryBus @Inject( )( information: QueryHandlerInformation, app: Application ) extends QueryBus {
  override def ask[ R <: Response ]( query: Query ): Future[ R ] = {
    try {
      val commandHandlerClass: Class[ _ <: QueryHandler[ _ <: Query, _ <: Response ] ] = information
        .search( query.getClass )
      
      val handler: QueryHandler[ Query, R ] = app.injector.instanceOf( commandHandlerClass )
                                                 .asInstanceOf[ QueryHandler[ Query, R ] ]
      
      handler.handle( query )
    } catch {
      case error: Throwable => throw new QueryHandlerExecutionError( error )
    }
  }
  
}
