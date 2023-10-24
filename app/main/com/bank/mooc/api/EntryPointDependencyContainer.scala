package com.bank.mooc.api

import com.bank.mooc.api.controller.account.{ AccountGetController, SendMoneyPutController }
import com.bank.shared.domain.bus.command.CommandBus
import com.bank.shared.domain.bus.query.QueryBus

final class EntryPointDependencyContainer(
  queryBus:   QueryBus,
  commandBus: CommandBus
) {
  
  val accountGetController   = new AccountGetController( queryBus )
  val sendMoneyPutController = new SendMoneyPutController( commandBus )
  
}
