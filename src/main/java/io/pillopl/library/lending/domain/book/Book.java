package io.pillopl.library.lending.domain.book;

import io.pillopl.library.catalogue.BookId;
import io.pillopl.library.lending.domain.patron.events.BookPlacedOnHold;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Optional;

import static io.pillopl.library.lending.domain.book.Book.State.Available;
import static io.pillopl.library.lending.domain.book.Book.State.OnHold;

@AllArgsConstructor
public class Book {

    @NonNull @Getter private BookId bookId;
    @NonNull private BookType bookType;
    @NonNull private Book.State bookLendingState;

    public enum State {
        OnHold, Collected, Available;
    }

    public Optional<AvailableBook> toAvailableBook() {
        if (!bookLendingState.equals(Available)) {
            return Optional.empty();
        }
        return Optional.of(new AvailableBook(bookId, bookType));
    }

    public void handle(BookPlacedOnHold bookPlacedOnHold) {
        bookLendingState = OnHold;
    }


}


