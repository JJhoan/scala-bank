package com.bank.shared.infrastructure.bus.event

import com.bank.shared.domain.DomainEventSubscriber
import org.reflections.Reflections

import scala.jdk.CollectionConverters.CollectionHasAsScala

final class DomainEventSubscribersInformation {
  
  private val scanDomainEventSubscribers: Map[ Class[ _ ], DomainEventSubscriberInformation ] = {
    val reflections = new Reflections( "com.bank" )
    val subscribers = reflections.getTypesAnnotatedWith( classOf[ DomainEventSubscriber ] ).asScala.toSet
    
    subscribers.map { subscriber =>
      val annotation = subscriber.getAnnotation( classOf[ DomainEventSubscriber ] )
      subscriber -> new DomainEventSubscriberInformation( subscriber, annotation.value().toSet )
    }.toMap
  }
  
  def search( eventClass: Class[ _ ] ): DomainEventSubscriberInformation = {
    val eventDomain = scanDomainEventSubscribers.get( eventClass )
    
    if ( eventDomain.isEmpty ) {
      throw new NullPointerException( )
    }
    
    eventDomain.get
  }
  
  def all: Map[ Class[ _ ], DomainEventSubscriberInformation ] = scanDomainEventSubscribers
}
