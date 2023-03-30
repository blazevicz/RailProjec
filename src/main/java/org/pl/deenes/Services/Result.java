package org.pl.deenes.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.Data.Line;
import org.pl.deenes.Data.RoadStats;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class Result {
    private CalculateKilometers calculateKilometers;
    private ReadKilometers readKilometers;

    public void voidTestingResultMethodForOneLine() throws IOException, ParseException {
        readKilometers.setFile(Files.myPatch());
        Kilometers reader = readKilometers.reader();

        reader.getAllRailwayLines();
        reader.giveAllKilometers();
        calculateKilometers.setKilometers(reader);
        RoadStats roadStats1 = calculateKilometers.calculateKilometers();
        log.info(("Dlugosc trasy " + roadStats1.getHowManyKilometers()));
        var list = new java.util.ArrayList<>(roadStats1.getLineList().stream().map(Line::getSize).filter(Objects::nonNull).toList());

        log.info(String.valueOf(list));

    }
}
