package org.example;


import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        ReadKilometers readKilometers = new ReadKilometers( new File("/Users/damianblazewicz/IdeaProjects/practice/rail/src/main/resources/RJ_SKRJ_666401_464028_9.pdf"));
        Kilometers reader = readKilometers.reader();
        reader.getAllRailwayLines();
        reader.giveAllKilometers();
        CalculateKilometers calculateKilometers = new CalculateKilometers(reader);
        RoadStats roadStats = calculateKilometers.calculateKilometers();
        System.out.println(roadStats.getHowManyKilometers());

        var list = new java.util.ArrayList<>(roadStats.getLineList().stream().map(Line::getSize).filter(Objects::nonNull).toList());
        Comparator<Line> asd = Comparator.comparing(Line::getSize);
        //list.sort(asd);
        System.out.println(list);



    }
}