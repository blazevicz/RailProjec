package org.pl.deenes.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.Data.Line;
import org.pl.deenes.Data.RoadStats;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class Result {
    private Kilometers kilometers;
    private CalculateKilometers calculateKilometers;
    //private RoadStats roadStats;


    public void voidTestingResultMethodForOneLine() {
        kilometers.getAllRailwayLines();
        kilometers.getAllKilometers();

        RoadStats roadStats = calculateKilometers.calculateKilometers();
        log.info(("Dlugosc trasy " + roadStats.getHowManyKilometers()));
        var list = new java.util.ArrayList<>(roadStats.getLineList().stream().map(Line::getSize).filter(Objects::nonNull).toList());

        log.info(String.valueOf(list));

    }
}
