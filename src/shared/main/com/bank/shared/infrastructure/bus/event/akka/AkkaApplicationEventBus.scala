package com.bank.shared.infrastructure.bus.event.akka

import akka.actor.ActorSystem
import com.bank.shared.domain.event.{ DomainEvent, EventBus }
import com.bank.shared.infrastructure.bus.event.DomainEventSubscribersInformation

import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

final class AkkaApplicationEventBus (
  actorSystem:                       ActorSystem,
  domainEventSubscribersInformation: DomainEventSubscribersInformation,
) extends EventBus {
  
  val application: Seq[Class[_]] = Seq.empty
  
  private val eventPublisherActor = actorSystem.actorOf(
    EventPublisherActor.props( domainEventSubscribersInformation, application ) )
  
  override def publish( events: mutable.Set[ DomainEvent ] ): Future[ Unit ] = {
    Future( events.foreach( publish ) )
  }
  
  private def publish( event: DomainEvent ): Future[ Unit ] = {
    Future( eventPublisherActor ! event )
  }
  
}
