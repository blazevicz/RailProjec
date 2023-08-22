package org.pl.deenes.infrastructure.repositories.dao;

import org.pl.deenes.model.Train;

import java.util.List;
import java.util.Optional;

public interface TrainDAO {
    Train save(Train train);

    Optional<Train> find(Integer trainKwr);

    void delete(Integer trainKwr);

    List<Train> findAll();
}
