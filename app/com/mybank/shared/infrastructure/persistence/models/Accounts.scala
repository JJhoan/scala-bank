package com.mybank.shared.infrastructure.persistence.models

import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ ProvenShape, Rep, Tag }

case class Accounts( id: Int, number: String, amount: BigDecimal )

object AccountsTable {
  
  class AccountsTable( tag: Tag ) extends Table[ Accounts ]( tag, "accounts" ) {
    
    val id    : Rep[ Int ]        = column[ Int ]( "id" )
    val number: Rep[ String ]     = column[ String ]( "number" )
    val amount: Rep[ BigDecimal ] = column[ BigDecimal ]( "amount" )
    
    override def * : ProvenShape[ Accounts ] = (id, number, amount) <> (Accounts.tupled, Accounts.unapply)
  }
}
