package com.mybank.shared.domain.bus.query

import com.mybank.shared.infrastructure.bus.query.QueryHandlerExecutionError

import scala.concurrent.Future

trait QueryBus {
  @throws[QueryHandlerExecutionError]
  def ask[R <: Response]( query: Query ): Future[R]
}
