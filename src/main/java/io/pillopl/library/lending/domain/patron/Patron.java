package io.pillopl.library.lending.domain.patron;

import io.pillopl.library.catalogue.BookId;
import io.pillopl.library.lending.domain.book.AvailableBook;
import io.pillopl.library.lending.domain.patron.events.BookCollected;
import io.pillopl.library.lending.domain.patron.events.BookPlacedOnHold;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static io.pillopl.library.lending.domain.patron.PatronType.Researcher;


/**
 * Task #2: Implement this aggregate
 * Make PlacingOnHoldBookScenarios and CollectingPlacedOnHoldBookScenarios pass!
 * Remember that Fixtures, for instance aRegularPatron() should be changed so that it returns a regular patron in the meaning of your new model.
 * There is one scenario to implement in CollectingPlacedOnHoldBookScenarios too. Implement it, make sure it passes.
 * Think about why we pass AvailableBook instead of Book to placeOnHold() method. Does it have any benefit?
 */
@AllArgsConstructor
public class Patron {

    @NonNull
    @Getter
    final PatronId patronId;

    final PatronType type;

    Holds patronHolds;

    OverdueCollections overdueCollections;

    public Optional<BookPlacedOnHold> placeOnHold(AvailableBook book, HoldDuration holdDuration) {
        if (holdDuration.isOpenEnded() && !isResearcher()) {
            return Optional.empty();
        }
        if (book.isRestricted() && !isResearcher()) {
            return Optional.empty();
        }
        if (holds() >= 5) {
            return Optional.empty();
        }
        if (overdueCollections() >= 2) {
            return Optional.empty();
        }
        patronHolds = patronHolds.with(book.getBookId());
        return Optional.of(new BookPlacedOnHold(book.getBookId(), patronId, holdDuration.getDays().orElse(null)));

    }


    public Optional<BookCollected> collect(BookId bookId, CollectDuration collectDuration) {
        if (patronHolds.a(bookId)) {
            patronHolds = patronHolds.without(bookId);
            return Optional.of(new BookCollected(bookId, patronId, collectDuration.getPeriod().getDays()));
        }
        return Optional.empty();
    }


    int holds() {
        return patronHolds.count();
    }

    int overdueCollections() {
        return overdueCollections.getOverdueCollections();
    }

    boolean isResearcher() {
        return type.equals(Researcher);
    }
}

@Value
class OverdueCollections {

    static OverdueCollections none() {
        return new OverdueCollections(0);
    }

    OverdueCollections(int overdueCollections) {
        if (overdueCollections < 0) {
            throw new IllegalArgumentException();
        }
        this.overdueCollections = overdueCollections;
    }

    int overdueCollections;

}

@Value
class Holds {

    Set<BookId> books;

    Holds with(BookId bookId) {
        Set<BookId> newBooks = new HashSet<>(books);
        newBooks.add(bookId);
        return new Holds(newBooks);
    }

    Holds without(BookId bookId) {
        Set<BookId> newBooks = new HashSet<>(books);
        newBooks.remove(bookId);
        return new Holds(newBooks);
    }

    boolean a(BookId bookId) {
        return books.contains(bookId);
    }

    int count() {
        return books.size();
    }
}






