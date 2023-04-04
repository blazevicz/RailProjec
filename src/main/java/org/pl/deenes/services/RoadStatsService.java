package org.pl.deenes.services;

import org.pl.deenes.data.RoadStats;

public interface RoadStatsService {
    RoadStats calculateKilometers(Double lastKilometer);
}
