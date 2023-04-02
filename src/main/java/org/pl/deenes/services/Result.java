package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.data.Analyse;
import org.pl.deenes.data.RoadStats;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class Result {
    private CalculateKilometers calculateKilometers;
    private ReadKilometers readKilometers;
    private RoadStatsService roadStatsService;
    private AnalyseService analyseService;

    public void voidTestingResultMethodForOneLine() {
        readKilometers.setFile(Files.myPatch());


        Kilometers reader = readKilometers.reader();

        reader.getAllRailwayLines();
        reader.giveAllKilometers();
        calculateKilometers.setKilometers(reader);

        RoadStats roadStats = roadStatsService.calculateKilometers(roadStatsService.getLastKilometer());
        log.info(("Dlugosc trasy " + roadStats.getHowManyKilometers()));

        Analyse analyse = analyseService.creatingTrainAnalyse();
        log.info(analyseService.creatingTrainAnalyse().toString());


    }
}
