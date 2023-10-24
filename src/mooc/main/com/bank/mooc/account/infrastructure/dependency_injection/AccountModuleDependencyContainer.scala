package com.bank.mooc.account.infrastructure.dependency_injection

import com.bank.mooc.account.application.deposite_money.{ MoneyDepositor, MoneyDepositorCommandHandler }
import com.bank.mooc.account.application.find.{ AccountFinder, AccountFinderQueryHandler }
import com.bank.mooc.account.application.send_money.{ MoneySender, MoneySenderCommandHandler }
import com.bank.mooc.account.application.withdraw_money.{ MoneyWithdrawal, MoneyWithdrawalCommandHandler }
import com.bank.mooc.account.domain.AccountRepository
import com.bank.mooc.account.infrastructure.repository.DoobieMySqlAccountRepository
import com.bank.shared.domain.bus.command.{ Command, CommandHandler }
import com.bank.shared.domain.bus.query.{ Query, QueryBus, QueryHandler, Response }
import com.bank.shared.domain.event.EventBus
import com.bank.shared.infrastructure.doobie.DoobieDbConnection
import com.mybank.account.application.withdraw_money.MoneyWithdrawalCommandHandler

import scala.concurrent.ExecutionContext

final class AccountModuleDependencyContainer(
  implicit doobieDbConnection: DoobieDbConnection,
  executionContext:            ExecutionContext,
  eventBus:                    EventBus,
  queryBus:                    QueryBus
) {
  val repository: AccountRepository = new DoobieMySqlAccountRepository( )
  
  implicit val accountFinder  : AccountFinder   = new AccountFinder( repository )
  implicit val moneyDepositor : MoneyDepositor  = new MoneyDepositor( eventBus, repository )
  implicit val moneySender    : MoneySender     = new MoneySender( repository, eventBus )
  implicit val moneyWithdrawal: MoneyWithdrawal = new MoneyWithdrawal( queryBus, eventBus, repository )
  
  private val accountFinderQueryHandler    : AccountFinderQueryHandler     = new AccountFinderQueryHandler
  private val moneyDepositorCommandHandler : MoneyDepositorCommandHandler  = new MoneyDepositorCommandHandler
  private val moneySenderCommandHandler    : MoneySenderCommandHandler     = new MoneySenderCommandHandler
  private val moneyWithdrawalCommandHandler: MoneyWithdrawalCommandHandler = new MoneyWithdrawalCommandHandler
  
  val commands: Seq[ CommandHandler[ Command ] ] = Seq[ CommandHandler[ Command ] ](
    accountFinderQueryHandler,
    moneyDepositorCommandHandler,
    moneySenderCommandHandler,
    moneyWithdrawalCommandHandler
  )
  
  val queries: Seq[QueryHandler[ Query, Response ]] = Seq[QueryHandler[ Query, Response ]](
    accountFinderQueryHandler
  )
  
}
