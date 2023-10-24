package com.bank.mooc.shared.infrastructure.persistence.models

import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ ProvenShape, Rep, Tag }

import java.time.LocalDateTime
import java.util.UUID

case class Transactions(
  id:          UUID,
  accountId:   Int,
  amount:      BigDecimal,
  description: String,
  date:        LocalDateTime )


class TransactionsTable( tag: Tag ) extends Table[ Transactions ]( tag, "transactions" ) {
  
  val id         : Rep[ UUID ]          = column[ UUID ]( "id" )
  val accountId  : Rep[ Int ]           = column[ Int ]( "account_id" )
  val amount     : Rep[ BigDecimal ]    = column[ BigDecimal ]( "amount" )
  val description: Rep[ String ]        = column[ String ]( "description" )
  val date       : Rep[ LocalDateTime ] = column[ LocalDateTime ]( "date" )
  
  private def applyTuple( t: (UUID, Int, BigDecimal, String, LocalDateTime) ): Transactions = Transactions.tupled( t )
  
  override def * : ProvenShape[ Transactions ] = {
    (id, accountId, amount, description, date).<>(applyTuple, Transactions.unapply)
  }
}
