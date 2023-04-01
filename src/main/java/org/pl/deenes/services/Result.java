package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.data.Line;
import org.pl.deenes.data.RoadStats;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class Result {
    private CalculateKilometers calculateKilometers;
    private ReadKilometers readKilometers;
    private RoadStatsService roadStatsService;

    public void voidTestingResultMethodForOneLine() throws IOException, ParseException {
        readKilometers.setFile(Files.myPatch());
        Kilometers reader = readKilometers.reader();

        reader.getAllRailwayLines();
        reader.giveAllKilometers();
        calculateKilometers.setKilometers(reader);

        RoadStats roadStats1 = roadStatsService.calculateKilometers();
        log.info(("Dlugosc trasy " + roadStats1.getHowManyKilometers()));



        Map<Integer, List<Line>> mapaLinii = roadStats1.getLineList().stream()
                .collect(Collectors.groupingBy(Line::getLineNumber,
                        Collectors.mapping(line -> line, Collectors.toList())));

        List<Line> listaPolaczonychLinii = mapaLinii.values().stream()
                .flatMap(List::stream)
                .toList();

        listaPolaczonychLinii.forEach(System.out::println);

    }
}
