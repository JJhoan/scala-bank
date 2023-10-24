package com.bank.shared.infrastructure.bus.event.akka

import akka.actor.{ Actor, ActorLogging, Props }
import com.bank.shared.domain.event.DomainEvent
import com.bank.shared.infrastructure.bus.event.DomainEventSubscribersInformation

final class EventPublisherActor(
  subscribersInformation: DomainEventSubscribersInformation,
  app: Seq[Class[_]]
) extends Actor with ActorLogging {
  
  def receive: Receive = {
    case event: DomainEvent =>
      val subscribersEvents = subscribersInformation.all.values.filter(
        _.subscribedEventsInformation.contains( event.getClass ) ).map( _.subscriber ).toSet
      
      subscribersEvents.foreach{ subscriber =>
        val objectSubscriber = app.filter( subscriber.equals(_) )
        val subscriberOnMethod = objectSubscriber.getClass.getMethod( "on", event.getClass )
        subscriberOnMethod.invoke( objectSubscriber, event )
      }
  }
  
}

object EventPublisherActor {
  def props(
    subscribersInformation:  DomainEventSubscribersInformation,
    app:                     Seq[Class[_]]
  ): Props = {
    Props( new EventPublisherActor( subscribersInformation, app ) )
  }
}