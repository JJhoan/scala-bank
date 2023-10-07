package com.mybank.shared.infrastructure

import com.google.inject.AbstractModule
import com.mybank.account.domain.AccountRepository
import com.mybank.shared.domain.bus.command.CommandBus
import com.mybank.shared.domain.bus.query.QueryBus
import com.mybank.shared.domain.event.EventBus
import com.mybank.shared.infrastructure.bus.command.InMemoryCommandBus
import com.mybank.shared.infrastructure.bus.event.akka.AkkaApplicationEventBus
import com.mybank.shared.infrastructure.bus.query.InMemoryQueryBus
import com.mybank.shared.infrastructure.persistence.PostgrestDatabase
import com.mybank.transaction.domain.TransactionRepository

class MyModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[AccountRepository]).to( classOf[PostgrestDatabase])
    bind(classOf[TransactionRepository]).to( classOf[PostgrestDatabase])
    bind(classOf[CommandBus]).to(classOf[InMemoryCommandBus])
    bind(classOf[QueryBus]).to(classOf[InMemoryQueryBus])
    bind(classOf[EventBus]).to(classOf[AkkaApplicationEventBus])
  }
}