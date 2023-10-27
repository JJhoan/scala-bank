package com.bank.mooc.account.domain

import com.bank.shared.domain.IntMother

object AccountAmountMother {
  
  def apply(
    value: BigDecimal = BigDecimal( IntMother.randomBetween( 100, 1000 ) )
  ): AccountAmount = {
    AccountAmount( value )
  }
  
  def random: AccountAmount = this ( )
  
}
