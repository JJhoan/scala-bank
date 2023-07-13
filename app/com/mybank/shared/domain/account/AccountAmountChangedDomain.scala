package com.mybank.shared.domain.account

import com.mybank.shared.infrastructure.bus.event.DomainEvent

case class AccountAmountChangedDomain(
  aggregateId: String,
  eventId:     Option[ String ] = None,
  number:      String,
  amount:      BigDecimal,
  description: String,
) extends DomainEvent( aggregateId, eventId ) {
  
  def this( ) = {
    this( "", None, "", 0.0, "" )
  }
  
  override def eventName: String = "accountAmount.changed"
  
  override def toPrimitives: Map[ String, Serializable ] = {
    Map(
      "number" -> number,
      "amount" -> amount,
    )
  }
  
  override def fromPrimitives( aggregateId: String, body: Map[ String, Serializable ], eventId: String ): DomainEvent = {
    AccountAmountChangedDomain(
      aggregateId,
      Option( eventId ),
      body( "number" ).asInstanceOf[ String ],
      body( "amount" ).asInstanceOf[ BigDecimal ],
      body( "description" ).asInstanceOf[ String ]
    )
  }
}
