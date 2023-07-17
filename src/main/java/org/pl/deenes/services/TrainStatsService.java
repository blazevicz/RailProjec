package org.pl.deenes.services;

import org.pl.deenes.model.TrainStats;

public interface TrainStatsService {
    TrainStats calculateKilometers(Double lastKilometer);
}
