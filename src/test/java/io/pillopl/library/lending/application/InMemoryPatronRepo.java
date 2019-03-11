package io.pillopl.library.lending.application;

import io.pillopl.library.lending.domain.patron.Patron;
import io.pillopl.library.lending.domain.patron.PatronId;
import io.pillopl.library.lending.domain.patron.PatronRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class InMemoryPatronRepo implements PatronRepository {

    private final Map<PatronId, Patron> database = new HashMap<>();

    @Override
    public Optional<Patron> findById(PatronId patronId) {
        return Optional.ofNullable(database.get(patronId));
    }

    @Override
    public void save(Patron patron) {
        database.put(patron.getPatronId(), patron);
    }
}
