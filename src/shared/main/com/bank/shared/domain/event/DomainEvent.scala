package com.bank.shared.domain.event

import java.util.UUID

abstract class DomainEvent( aggregateId: String, eventId: Option[String] = Some(UUID.randomUUID.toString) ) {

  def eventName: String

  def toPrimitives: Map[String, Serializable]

  def fromPrimitives(
    aggregateId: String,
    body: Map[String, Serializable],
    eventId: String,
  ): DomainEvent

}