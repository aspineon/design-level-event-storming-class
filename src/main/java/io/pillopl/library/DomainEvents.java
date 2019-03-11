package io.pillopl.library;

import io.pillopl.library.lending.domain.patron.events.DomainEvent;

public interface DomainEvents {

    void publish(DomainEvent domainEvent);
}
