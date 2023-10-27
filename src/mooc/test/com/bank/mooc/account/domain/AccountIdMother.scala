package com.bank.mooc.account.domain

import com.bank.shared.domain.IntMother

object AccountIdMother {
  def apply( value: Int ): AccountId = AccountId( value )
  
  def random: AccountId = AccountId( IntMother.random )
}
