package com.mybank.shared.infrastructure.bus.event

final class DomainEventSubscriberInformation( subscriberClass: Class[_], subscribedEvents: Set[ Class[ _ <: DomainEvent ] ]) {
  def contextName: String = {
    val nameParts = subscriberClass.getName.split( "\\." )
    nameParts( 2 )
  }
  
  def moduleName: String = {
    val nameParts = subscriberClass.getName.split( "\\." )
    nameParts( 3 )
  }
  
  def className: String = {
    val nameParts = subscriberClass.getName.split( "\\." )
    nameParts( nameParts.length - 1 )
  }
  
  def subscribedEventsInformation: Set[ Class[ _ <: DomainEvent ] ] = {
    subscribedEvents
  }
  
  def subscriber: Class[ _ ] = subscriberClass
}
