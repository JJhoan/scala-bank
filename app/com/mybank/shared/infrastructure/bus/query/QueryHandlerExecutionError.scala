package com.mybank.shared.infrastructure.bus.query

final class QueryHandlerExecutionError( cause: Throwable ) extends RuntimeException( cause )
