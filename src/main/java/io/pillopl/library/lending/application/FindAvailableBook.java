package io.pillopl.library.lending.application;

import io.pillopl.library.catalogue.BookId;
import io.pillopl.library.lending.domain.book.AvailableBook;

import java.util.Optional;

public interface FindAvailableBook {

    Optional<AvailableBook> find(BookId bookId);
}
