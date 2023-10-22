package com.bank.mooc.transaction.domain

object TransactionDescription extends Enumeration {
  type TransactionDescription = Value
  
  val TransactionTo: TransactionDescription.Value = Value("Transfer to myBank account")
  val TransactionFrom: TransactionDescription.Value = Value("Transfer from myBank account")
  val TransactionTaxes: TransactionDescription.Value = Value("Government tax")
  
}