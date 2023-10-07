package com.mybank.shared.infrastructure.bus.event.akka

import akka.actor.{ Actor, ActorLogging, Props }
import com.mybank.shared.domain.event.DomainEvent
import com.mybank.shared.infrastructure.bus.event.DomainEventSubscribersInformation
import play.api.Application

final class EventPublisherActor(
  subscribersInformation:  DomainEventSubscribersInformation,
  app:                     Application
) extends Actor with ActorLogging {
  
  def receive: Receive = {
    case event: DomainEvent =>
      val subscribersEvents = subscribersInformation.all.values.filter(
        _.subscribedEventsInformation.contains( event.getClass ) ).map( _.subscriber ).toSet
      
      subscribersEvents.foreach{ subscriber =>
        val objectSubscriber = app.injector.instanceOf( subscriber )
        val subscriberOnMethod = objectSubscriber.getClass.getMethod( "on", event.getClass )
        subscriberOnMethod.invoke( objectSubscriber, event )
      }
    
    
  }
  
}

object EventPublisherActor {
  def props(
    subscribersInformation:  DomainEventSubscribersInformation,
    app:                     Application
  ): Props = {
    Props( new EventPublisherActor( subscribersInformation, app ) )
  }
}