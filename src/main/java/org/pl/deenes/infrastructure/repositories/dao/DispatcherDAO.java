package org.pl.deenes.infrastructure.repositories.dao;

import org.pl.deenes.model.Dispatcher;

import java.util.List;
import java.util.Optional;

public interface DispatcherDAO {
    Dispatcher save(Dispatcher dispatcher);

    Optional<Dispatcher> findBySurname(String surname);
    List<Dispatcher> findAll();

}
