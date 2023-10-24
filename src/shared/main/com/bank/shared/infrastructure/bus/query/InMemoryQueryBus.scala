package com.bank.shared.infrastructure.bus.query

import com.bank.shared.domain.bus.query.{ Query, QueryBus, QueryHandler, Response }
import com.bank.shared.infrastructure.dependency_injection.SharedModuleDependencyContainer

import scala.concurrent.Future

final class InMemoryQueryBus ( information: QueryHandlerInformation, sharedModule: SharedModuleDependencyContainer ) extends QueryBus {
  override def ask[ R <: Response ]( query: Query ): Future[ R ] = {
    try {
      val commandHandlerClass: Class[ _ <: QueryHandler[ _ <: Query, _ <: R ] ] = information.search( query.getClass ).asInstanceOf[Class[ _ <: QueryHandler[ _ <: Query, _ <: R ]]]
      val handler: QueryHandler[ Query, R ] = sharedModule.queries.find( q => q.getClass.isInstance(commandHandlerClass)).get.asInstanceOf[QueryHandler[ Query, R ]]
      
      handler.handle( query )
    } catch {
      case error: Throwable => throw new QueryHandlerExecutionError( error )
    }
  }
  
}
