package org.pl.deenes.services;

import org.pl.deenes.model.TrainStats;

public interface RoadStatsService {
    TrainStats calculateKilometers(Double lastKilometer);
}
