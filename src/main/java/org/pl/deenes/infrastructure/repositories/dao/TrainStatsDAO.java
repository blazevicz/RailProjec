package org.pl.deenes.infrastructure.repositories.dao;

import org.pl.deenes.model.TrainStats;

import java.util.Optional;

public interface TrainStatsDAO {

    Optional<TrainStats> findAllByTrainStatsId(Integer id);

}
