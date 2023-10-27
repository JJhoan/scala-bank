package com.bank.mooc.account.domain

import com.bank.shared.domain.StringMother

object AccountNumberMother {
  
  def apply( value: String = StringMother.random ): AccountNumber = AccountNumber( value )
  
  def random: AccountNumber = this ( )
  
}
