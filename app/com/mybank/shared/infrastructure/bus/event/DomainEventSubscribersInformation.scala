package com.mybank.shared.infrastructure.bus.event

import com.mybank.shared.domain.{ DomainEventSubscriber, Singleton }
import org.reflections.Reflections

import scala.jdk.CollectionConverters.CollectionHasAsScala

@Singleton
final class DomainEventSubscribersInformation {
  
  private val scanDomainEventSubscribers: Map[ Class[ _ ], DomainEventSubscriberInformation ] = {
    val reflections = new Reflections( "com.mybank" )
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
  
  def all: Map[ Class[ _ ], DomainEventSubscriberInformation ] = {
    scanDomainEventSubscribers
  }
}
