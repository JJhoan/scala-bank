package com.bank.mooc.transaction.infrastructure.dependency_injection

import com.bank.mooc.transaction.application.create.{ CreateTransaction, CreateTransactionCommandHandler }
import com.bank.mooc.transaction.application.find.{ TransactionFinder, TransactionFinderQueryHandler }
import com.bank.mooc.transaction.domain.TransactionRepository
import com.bank.mooc.transaction.infrastructure.repository.DoobieMySqlTransactionRepository
import com.bank.shared.domain.bus.command.{ Command, CommandHandler }
import com.bank.shared.domain.bus.query.{ Query, QueryBus, QueryHandler, Response }
import com.bank.shared.domain.event.EventBus
import com.bank.shared.infrastructure.doobie.DoobieDbConnection

import scala.concurrent.ExecutionContext

final class TransactionModuleDependencyContainer(
  implicit doobieDbConnection: DoobieDbConnection,
  executionContext:            ExecutionContext,
  eventBus:                    EventBus,
  queryBus:                    QueryBus
) {
  val repository: TransactionRepository = new DoobieMySqlTransactionRepository
  
  implicit val createTransaction: CreateTransaction = new CreateTransaction( repository )
  implicit val transactionFinder: TransactionFinder = new TransactionFinder( repository )
  
  private val createTransactionCommandHandler = new CreateTransactionCommandHandler
  private val transactionFinderCommandHandler = new TransactionFinderQueryHandler
  
  val commands: Seq[ CommandHandler[ Command ] ] = Seq[ CommandHandler[ Command ] ](
    createTransactionCommandHandler,
    transactionFinderCommandHandler
  )
  
  val queries: Seq[QueryHandler[ Query, Response ]] = Seq.empty
  
}
