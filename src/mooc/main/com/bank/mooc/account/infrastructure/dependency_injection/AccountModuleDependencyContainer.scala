package com.bank.mooc.account.infrastructure.dependency_injection

import com.bank.mooc.account.application.deposite_money.{ MoneyDepositor, MoneyDepositorCommandHandler }
import com.bank.mooc.account.application.find.{ AccountFinder, AccountFinderQueryHandler }
import com.bank.mooc.account.application.send_money.MoneySender
import com.bank.mooc.account.application.withdraw_money.MoneyWithdrawal
import com.bank.mooc.account.domain.AccountRepository
import com.bank.mooc.account.infrastructure.repository.DoobieMySqlAccountRepository
import com.bank.shared.domain.bus.query.QueryBus
import com.bank.shared.domain.event.EventBus
import com.bank.shared.infrastructure.doobie.DoobieDbConnection

import scala.concurrent.ExecutionContext

final class AccountModuleDependencyContainer(
  implicit doobieDbConnection: DoobieDbConnection,
  executionContext:            ExecutionContext,
  eventBus:                    EventBus,
  queryBus:                    QueryBus
) {
  val repository: AccountRepository = new DoobieMySqlAccountRepository( )
  
  val accountFinder  : AccountFinder   = new AccountFinder( repository )
  val moneyDepositor : MoneyDepositor  = new MoneyDepositor( eventBus, repository )
  val moneySender    : MoneySender     = new MoneySender( repository, eventBus )
  val moneyWithdrawal: MoneyWithdrawal = new MoneyWithdrawal( queryBus, eventBus, repository )
  
  val accountFinderQueryHandler: AccountFinderQueryHandler = new AccountFinderQueryHandler(accountFinder)
  val moneyDepositorCommandHandler: MoneyDepositorCommandHandler = new MoneyDepositorCommandHandler(moneyDepositor)
  
}
