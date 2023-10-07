package com.mybank.shared.infrastructure.bus.event

import com.mybank.shared.domain.Singleton
import com.mybank.shared.domain.event.DomainEvent
import org.reflections.Reflections

import scala.jdk.CollectionConverters.CollectionHasAsScala

@Singleton
final class DomainEventsInformation {
  
  private val indexedDomainEvents: Map[ String, Class[ _ <: DomainEvent ] ] = {
    val reflections: Reflections                      = new Reflections( "com.mybank" )
    val classes    : Set[ Class[ _ <: DomainEvent ] ] = reflections.getSubTypesOf( classOf[ DomainEvent ]
    ).asScala.toSet
    
    formatEvents( classes )
  }
  
  private def formatEvents( domainEvents: Set[ Class[ _ <: DomainEvent ] ] ): Map[ String, Class[ _ <: DomainEvent ] ] = {
    domainEvents.map{ domainEvent =>
      val nullInstance = domainEvent.getConstructor( ).newInstance( )
      domainEvent.getMethod( "eventName" ).invoke( nullInstance ).asInstanceOf[ String ] -> domainEvent
    }.toMap
  }
  
  def forName( name: String ): Class[ _ <: DomainEvent ] = indexedDomainEvents( name )
  
  def forClass( domainEventClass: Class[ _ <: DomainEvent ] ): String = {
    indexedDomainEvents.filter{
      case (_, value) => value.equals( domainEventClass )
    }.keys.head
  }
  
}
