package io.pillopl.library.lending.domain.patron.events;

import io.pillopl.library.catalogue.BookId;
import io.pillopl.library.lending.domain.patron.PatronId;
import lombok.Value;

@Value
public class BookPlacedOnHold implements DomainEvent {
    BookId bookId;
    PatronId patronId;
    Integer forDays;
}
