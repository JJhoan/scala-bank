package com.mybank.shared.infrastructure.bus.event

import scala.collection.mutable
import scala.concurrent.Future

trait EventBus {
  
  def publish( events: mutable.Set[ DomainEvent ] ): Future[Unit]
  
}
