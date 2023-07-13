package com.mybank.transaction.infrastructure.controller

import com.google.inject.Inject
import com.mybank.shared.domain.Singleton
import com.mybank.shared.domain.bus.query.QueryBus
import com.mybank.shared.infrastructure.controller.ApiController
import com.mybank.transaction.application.find
import com.mybank.transaction.application.find.{ TransactionsFinderQuery, TransactionsResponse }
import play.api.libs.json.{ Json, OWrites }
import play.api.mvc.{ Action, AnyContent, ControllerComponents }

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
final class TransactionsGetController @Inject( )( cc: ControllerComponents, queryBus: QueryBus )
  extends ApiController( cc ) {
  
  def index( accountId: Int ): Action[ AnyContent ] = {
    cc.actionBuilder.async{
      for {
        transactionsQuery <- queryBus.ask[ find.TransactionsResponse ]( TransactionsFinderQuery( accountId ) )
        transactions = toResponse( transactionsQuery )
      } yield Ok( Json.toJson( transactions ) )
    }
  }
  
  private def toResponse( transactionsResponse: TransactionsResponse ): Seq[ TransactionResponse ] = {
    transactionsResponse
      .transactions
      .map( transaction =>
        TransactionResponse(
          transaction.accountId,
          transaction.amount,
          transaction.description
        )
      )
  }
  
  case class TransactionResponse( accountId: Int, amount: BigDecimal, description: String )
  implicit val requestJson: OWrites[ TransactionResponse ] = Json.writes[ TransactionResponse ]
  
  
  
}
