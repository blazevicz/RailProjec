package org.example;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Objects;
@Slf4j
public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        ReadKilometers readKilometers = new ReadKilometers( Files.myPatch());
        Kilometers reader = readKilometers.reader();
        reader.getAllRailwayLines();
        reader.giveAllKilometers();
        CalculateKilometers calculateKilometers = new CalculateKilometers(reader);
        RoadStats roadStats = calculateKilometers.calculateKilometers();
        log.info(String.valueOf("Dlugosc trasy " + roadStats.getHowManyKilometers()));

        var list = new java.util.ArrayList<>(roadStats.getLineList().stream().map(Line::getSize).filter(Objects::nonNull).toList());
        Comparator<Line> asd = Comparator.comparing(Line::getSize);
        //list.sort(asd);
        log.info(String.valueOf(list));



    }
}