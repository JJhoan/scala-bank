package com.bank.shared.infrastructure.bus.query

import com.bank.shared.domain.bus.query.Query

final class QueryNotRegisteredError( query: Class[_ <: Query] )
  extends Exception( s"The query <${ query.toString }> hasn't a query handler associated" )
