package io.pillopl.library.lending.application.readmodel

import io.pillopl.library.catalogue.BookId
import io.pillopl.library.lending.domain.patron.Fixtures
import io.pillopl.library.lending.domain.patron.PatronId
import spock.lang.Specification

class PlacedOnHoldBooksViewTest extends Specification {

    PlacedOnHoldBooksView placedOnHoldView = new PlacedOnHoldBooksView()
    PatronId aPatron = Fixtures.anyPatronId()
    BookId aBook = Fixtures.anyBookId()

    def 'when patron places a book on hold, view is modified'() {
        given:
            //placedOnHoldView.handle(new BookPlacedOnHold(aBook, aPatron, 0))
        when:
            List<BookId> placedByPatron = placedOnHoldView.by(aPatron)
        then:
            placedByPatron.size() == 1
            placedByPatron.head() == aBook
    }

    /**
     * Task - add more scenarios, taking into account other events that affect this view
     */
}
