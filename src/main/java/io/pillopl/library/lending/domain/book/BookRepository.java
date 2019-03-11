package io.pillopl.library.lending.domain.book;

import io.pillopl.library.catalogue.BookId;

import java.util.Optional;

public interface BookRepository {

    Optional<Book> findById(BookId bookId);

    void save(Book book);

}


