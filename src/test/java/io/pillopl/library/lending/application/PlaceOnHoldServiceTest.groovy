package io.pillopl.library.lending.application

import io.pillopl.library.DomainEvents

import io.pillopl.library.lending.domain.book.AvailableBook
import io.pillopl.library.lending.domain.patron.Fixtures
import io.pillopl.library.lending.domain.patron.Patron
import io.pillopl.library.lending.domain.patron.PatronRepository
import io.pillopl.library.lending.domain.patron.events.BookPlacedOnHold
import org.junit.Test

import static io.pillopl.library.lending.domain.patron.HoldDuration.forTenDays
import static org.mockito.ArgumentMatchers.isA
import static org.mockito.Mockito.*

class PlacedOnHoldServiceTest {

    PatronRepository patronRepository = new InMemoryPatronRepo();
    DomainEvents events = mock(DomainEvents.class);
    PlaceOnHoldService placeOnHoldService = new PlaceOnHoldService(patronRepository, events);

    @Test
    void shouldPublishAnEventWhenOperationWasSuccessful() {
        //given
        Patron patron = persistedRegularPatron();
        //and
        AvailableBook book = availableBook();

        //when
        placeOnHoldService.placeOnHold(new PlaceOnHoldCommand(book.getBookId(), patron.getPatronId(), forTenDays()));

        //then
        verify(events).publish(isA(BookPlacedOnHold.class));
    }

    @Test
    void shouldNotPublishAnEventWhenOperationWasNotSuccessful() {
        //given
        Patron patron = persistedRegularPatron();
        //and
        AvailableBook book = availableRestrictedBook();

        //when
        placeOnHoldService.placeOnHold(new PlaceOnHoldCommand(book.getBookId(), patron.getPatronId(), forTenDays()));

        //then
        verifyZeroInteractions(events);
    }

    Patron persistedRegularPatron() {
        Patron patron = Fixtures.aRegularPatron();
        patronRepository.save(patron);
        return patron;
    }

    AvailableBook availableBook() {
        AvailableBook book = Fixtures.circulatingBook();
        //TODO stub something...
        return book;
    }

    AvailableBook availableRestrictedBook() {
        AvailableBook book = Fixtures.restrictedBook();
        //TODO stub something...
        return book;
    }

}