package com.mybank.account.domain

import com.mybank.shared.domain.AggregateRoot
import com.mybank.shared.domain.account.AccountAmountChangedDomain
import com.mybank.transaction.domain.TransactionDescription.TransactionDescription

final case class Account( id: AccountId, number: AccountNumber, amount: AccountAmount ) extends AggregateRoot {
  
  def withdraw( amount: AccountAmount, description: TransactionDescription ): Unit = {
    if ( this.amount hasBalance amount ) {
      throw new AccountInsufficientFunds( s"Account [${ number.value }] doesn't have sufficient funds" )
    }
    
    this.copy( amount = this.amount - amount )
    
    record( AccountAmountChangedDomain( id.toString, None, number.value, -amount.value, description.toString ) )
  }
  
  def deposit( amount: AccountAmount, description: TransactionDescription ): Unit = {
    this.copy( amount = this.amount + amount )
    
    record( AccountAmountChangedDomain( id.toString, None, number.value, amount.value, description.toString ) )
  }
  
  override def hashCode( ): Int = (id, number).hashCode( )
  
  override def equals( other: Any ): Boolean = {
    other match {
      case that: Account => this.id.equals( that.id ) && this.number.equals( that.number )
      case _ => false
    }
  }
}

object Account {
  def apply( id: Int, number: String, amount: BigDecimal ): Account = {
    val accountId     = AccountId( id )
    val accountNumber = AccountNumber( number )
    val accountAmount = AccountAmount( amount )
    
    new Account( accountId, accountNumber, accountAmount )
  }
}

