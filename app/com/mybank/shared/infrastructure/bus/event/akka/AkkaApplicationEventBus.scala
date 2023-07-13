package com.mybank.shared.infrastructure.bus.event.akka

import akka.actor.ActorSystem
import com.google.inject.Inject
import com.mybank.shared.domain.Singleton
import com.mybank.shared.infrastructure.bus.event.{ DomainEvent, DomainEventSubscribersInformation, EventBus }
import play.api.Application

import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
final class AkkaApplicationEventBus @Inject( )(
  actorSystem:                       ActorSystem,
  domainEventSubscribersInformation: DomainEventSubscribersInformation,
  app:                               Application
) extends EventBus {
  
  private val eventPublisherActor = actorSystem.actorOf(
    EventPublisherActor.props( domainEventSubscribersInformation, app ) )
  
  override def publish( events: mutable.Set[ DomainEvent ] ): Future[ Unit ] = {
    Future( events.foreach( publish ) )
  }
  
  private def publish( event: DomainEvent ): Future[ Unit ] = {
    Future( eventPublisherActor ! event )
  }
  
}
