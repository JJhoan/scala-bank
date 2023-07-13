package com.mybank.account.application.send_money

import com.mybank.account.application.deposite_money.DepositMoneyCommand
import com.mybank.account.application.withdraw_money.WithdrawMoneyCommand
import com.mybank.account.domain.{ AccountAmount, AccountId }
import com.mybank.shared.domain.Singleton
import com.mybank.shared.domain.bus.command.CommandBus
import com.mybank.shared.domain.bus.query.QueryBus
import com.mybank.tax.application.{ TaxResponse, TaxServiceQuery }
import com.mybank.transaction.domain.TransactionDescription._

import javax.inject.Inject
import javax.transaction.Transactional
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
final class SendMoney @Inject( )( commandBus: CommandBus, queryBus: QueryBus ) {
  
  @Transactional
  def execute( from: AccountId, to: AccountId, amount: AccountAmount ): Future[ Unit ] = {
    for {
      _ <- commandBus.dispatch( WithdrawMoneyCommand( from.value, amount.value, TransactionFrom.toString ) )
      _ <- commandBus.dispatch( DepositMoneyCommand( to.value, amount.value, TransactionTo.toString ) )
      _ <- calculateTaxes( from, amount )
    } yield ()
  }
  
  private def calculateTaxes( from: AccountId, amount: AccountAmount ): Future[ Unit ] = {
    for {
      taxes <- queryBus.ask[ TaxResponse ]( TaxServiceQuery( amount.value ) )
      _ <- commandBus.dispatch( WithdrawMoneyCommand( from.value, taxes.amount.value, TransactionTaxes.toString ) )
    } yield ()
    
  }
  
  
}
