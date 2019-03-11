package io.pillopl.library.lending.domain.patron;

import java.util.Optional;

public interface PatronRepository {

    Optional<Patron> findById(PatronId patronId);

    void save(Patron patron);

}


