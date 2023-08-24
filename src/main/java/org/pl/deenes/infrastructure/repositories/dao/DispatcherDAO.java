package org.pl.deenes.infrastructure.repositories.dao;

import org.pl.deenes.infrastructure.entity.DispatcherEntity;
import org.pl.deenes.model.Dispatcher;

import java.util.List;
import java.util.Optional;

public interface DispatcherDAO {
    Dispatcher save(Dispatcher dispatcher);
    Optional<DispatcherEntity> findBySurname(String surname);
    List<Dispatcher> findAll();

}
