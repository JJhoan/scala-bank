package com.mybank.shared.infrastructure

import com.google.inject.AbstractModule
import com.mybank.account.application.{ AccountSearchable, AccountUpdatable, Rollback }
import com.mybank.shared.domain.bus.command.CommandBus
import com.mybank.shared.domain.bus.query.QueryBus
import com.mybank.shared.infrastructure.bus.command.InMemoryCommandBus
import com.mybank.shared.infrastructure.bus.event.EventBus
import com.mybank.shared.infrastructure.bus.event.akka.AkkaApplicationEventBus
import com.mybank.shared.infrastructure.bus.query.InMemoryQueryBus
import com.mybank.shared.infrastructure.persistence.PostgrestDatabase
import com.mybank.transaction.application.create.TransactionCreatable
import com.mybank.transaction.application.find.TransactionsSearchable

class MyModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[AccountSearchable]).to(classOf[PostgrestDatabase])
    bind(classOf[AccountUpdatable]).to(classOf[PostgrestDatabase])
    bind(classOf[Rollback]).to( classOf[PostgrestDatabase])
    bind(classOf[TransactionCreatable]).to(classOf[PostgrestDatabase])
    bind(classOf[TransactionsSearchable]).to(classOf[PostgrestDatabase])
    bind(classOf[CommandBus]).to(classOf[InMemoryCommandBus])
    bind(classOf[QueryBus]).to(classOf[InMemoryQueryBus])
    bind(classOf[EventBus]).to(classOf[AkkaApplicationEventBus])
  }
}