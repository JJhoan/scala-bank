package com.mybank.shared.infrastructure.bus.event.postgres

import com.google.inject.Inject
import com.mybank.shared.domain.Singleton
import com.mybank.shared.infrastructure.bus.event.DomainEventSubscribersInformation

@Singleton
final class MySqlEventBus @Inject() (
  domainEventSubscribersInformation: DomainEventSubscribersInformation,
){


}
