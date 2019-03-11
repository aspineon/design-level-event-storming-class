package io.pillopl.library.lending.domain.patron;

import io.pillopl.library.catalogue.BookId;
import io.pillopl.library.lending.domain.book.AvailableBook;
import io.pillopl.library.lending.domain.patron.events.BookCollected;
import io.pillopl.library.lending.domain.patron.events.BookPlacedOnHold;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;


/**
 * Task: Implement this aggregate
 * Make PlacingOnHoldBookScenarios and CollectingPlacedOnHoldBookScenarios pass!
 * Remember that Fixtures, for instance aRegularPatron() should be changed so that it returns a regular patron in the meaning of your new model.
 * Think about why we pass AvailableBook instead of Book to placeOnHold() method. Does it have any benefit?
 */
@AllArgsConstructor
public class Patron {

    @Getter
    final PatronId patronId;

    public Optional<BookPlacedOnHold> placeOnHold(AvailableBook book, HoldDuration holdDuration) {
        return Optional.empty();
    }


    public Optional<BookCollected> collect(BookId bookId, CollectDuration collectDuration) {
        return Optional.empty();
    }


}




