package io.pillopl.library.lending.application.readmodel;

import io.pillopl.library.catalogue.BookId;
import io.pillopl.library.lending.domain.patron.PatronId;

import java.util.List;

public class PlacedOnHoldBooksView {

    List<BookId> by(PatronId patron) {
        return null;
    }

    /**
     * Task:
     * add event method handler to events which modify this view.
     * For instance:
     * void handle(PlacedOnHoldEvent event)
     *
     * then implement those method (choose some in memory structure)
     *
     * then make sure test in PlacedOnHoldBooksView pass
     *
     * then add more scenarios
     *
     */
}


