package com.mybank.tax.application

import com.mybank.account.domain.AccountAmount
import com.mybank.shared.domain.Singleton

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
final class TaxService @Inject( )( ) {
  
  def execute( accountAmount: AccountAmount ): Future[ TaxResponse ] = {
    if ( accountAmount < 50000 ) return Future.successful( TaxResponse( 0 ) )
    Future( TaxResponse( accountAmount * 0.04 ) )
  }
}
