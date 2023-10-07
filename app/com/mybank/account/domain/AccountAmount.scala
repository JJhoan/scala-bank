package com.mybank.account.domain

import com.mybank.tax.domain.TaxAmount

case class AccountAmount( value: BigDecimal ) {
  
  override def toString: String = value.toString( )
  
  if ( value < 0 ) {
    throw new IllegalArgumentException( "Amount cannot be null" )
  }
  
  def -( amount: AccountAmount ): AccountAmount = AccountAmount( value - amount.value )
  
  def +( amount: AccountAmount ): AccountAmount = AccountAmount( value + amount.value )
  
  def >( amount: AccountAmount ): Boolean = value > amount.value
  
  def >( amount: BigDecimal ): Boolean = value > amount
  
  def <( amount: AccountAmount ): Boolean = value < amount.value
  
  def <( amount: BigDecimal ): Boolean = value < amount
  
  def *( amount: AccountAmount ): AccountAmount = AccountAmount( value * amount.value )
  
  def *( amount: TaxAmount ): AccountAmount = AccountAmount( value * amount.value )
  
  def *( amount: BigDecimal ): AccountAmount = AccountAmount( value * amount )
  
  def hasBalance( amount: AccountAmount ): Boolean = value >= amount.value
}
