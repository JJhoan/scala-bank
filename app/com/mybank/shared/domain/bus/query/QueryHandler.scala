package com.mybank.shared.domain.bus.query

import scala.concurrent.Future

trait QueryHandler[Q <: Query, R  <: Response]{
  def handle(query: Q): Future[R]
}
