package com.mybank.shared.domain;


import com.mybank.shared.infrastructure.bus.event.DomainEvent;

import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface DomainEventSubscriber {
    Class<? extends DomainEvent>[] value();
}
