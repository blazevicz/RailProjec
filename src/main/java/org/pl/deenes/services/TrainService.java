package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import org.pl.deenes.data.RoadStats;
import org.pl.deenes.data.Train;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TrainService {
    private RoadStats roadStats;

    public Train trainMaker() {
        return Train.builder()
                .lineList(roadStats.getLineList())
                .distance(roadStats.getHowManyKilometers()).build();
    }
}
