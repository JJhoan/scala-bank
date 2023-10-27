package com.bank.mooc.account.domain

import com.bank.shared.domain.SeqMother

object AccountMother {
  
  def apply(
    id:     AccountId = AccountIdMother.random,
    number: AccountNumber = AccountNumberMother.random,
    amount: AccountAmount = AccountAmountMother.random
  ): Account = {
    Account( id, number, amount )
  }
  
  def randomSeq: Seq[ Account ] = SeqMother.randomOf( apply( ) )
}
