package org.pl.deenes.infrastructure.repositories.dao;

import org.pl.deenes.model.Dispatcher;

import java.util.List;

public interface DispatcherDAO {
    Dispatcher save(Dispatcher dispatcher);

    List<Dispatcher> findAll();

}
