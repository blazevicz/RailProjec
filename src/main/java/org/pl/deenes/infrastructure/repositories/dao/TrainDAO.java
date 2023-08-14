package org.pl.deenes.infrastructure.repositories.dao;

import org.pl.deenes.model.Train;

import java.util.List;
public interface TrainDAO {
    Train save(Train train);

    List<Train> find(Integer trainKwr);

    void delete(Integer trainKwr);

    List<Train> findAll();
}
