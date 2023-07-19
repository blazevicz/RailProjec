package org.pl.deenes.services.interfaces;

import org.pl.deenes.model.TrainStats;

public interface TrainStatsService {
    TrainStats calculateKilometers(Double lastKilometer);
}
