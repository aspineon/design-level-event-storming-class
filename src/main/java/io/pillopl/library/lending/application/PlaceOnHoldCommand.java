package io.pillopl.library.lending.application;

import io.pillopl.library.catalogue.BookId;
import io.pillopl.library.lending.domain.patron.HoldDuration;
import io.pillopl.library.lending.domain.patron.PatronId;
import lombok.NonNull;
import lombok.Value;

@Value
public class PlaceOnHoldCommand {

    @NonNull
    BookId bookId;
    @NonNull
    PatronId patronId;
    @NonNull
    HoldDuration holdDuration;
}
