package com.mybank.shared.domain

import com.mybank.shared.infrastructure.bus.event.DomainEvent

import scala.collection.mutable

abstract class AggregateRoot {
  private var domainEvents: mutable.Set[ DomainEvent ] = mutable.Set[ DomainEvent ]()
  
  final def pullDomainEvents: mutable.Set[ DomainEvent ] = {
    val events = domainEvents
    domainEvents = mutable.Set( )
    events
  }
  
  final protected def record( event: DomainEvent ): Unit = {
    domainEvents.add( event )
  }
}
