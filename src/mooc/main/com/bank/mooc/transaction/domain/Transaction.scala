package com.bank.mooc.transaction.domain

import com.bank.mooc.account.domain.AccountId
import com.bank.mooc.transaction.domain.TransactionDescription.TransactionDescription
import com.bank.shared.domain.AggregateRoot

import java.time.LocalDateTime
import java.util.UUID

final case class Transaction (
  id:          TransactionId,
  accountId:   AccountId,
  amount:      TransactionAmount,
  description: String,
  date:        TransactionDate
) extends AggregateRoot

object Transaction {
  def apply( account: Int, amount: BigDecimal, description: String ): Transaction = {
    val transactionId          = TransactionId( )
    val accountId              = AccountId( account )
    val transactionAmount      = TransactionAmount( amount )
    val transactionDescription = TransactionDescription.withName( description )
    val transactionDate        = TransactionDate( )
    
    new Transaction( transactionId, accountId, transactionAmount, transactionDescription.toString, transactionDate )
  }
  
  def apply( id: UUID, account: Int, amount: BigDecimal, description: String, date: LocalDateTime ): Transaction = {
    val transactionId          = TransactionId( id )
    val accountId              = AccountId( account )
    val transactionAmount      = TransactionAmount( amount )
    val transactionDescription = TransactionDescription.withName( description )
    val transactionDate        = TransactionDate( date )
    
    new Transaction( transactionId, accountId, transactionAmount, transactionDescription.toString, transactionDate )
  }
  
  def apply( account: AccountId, amount: TransactionAmount, description: TransactionDescription ): Transaction = {
    val transactionId   = TransactionId( UUID.randomUUID( ) )
    val transactionDate = TransactionDate( )
    
    new Transaction( transactionId, account, amount, description.toString, transactionDate )
  }
  
}
