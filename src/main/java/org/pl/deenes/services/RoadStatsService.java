package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import org.pl.deenes.data.Line;
import org.pl.deenes.data.RoadStats;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class RoadStatsService {
    public static Double lastKilometer;
    private CalculateKilometers calculateKilometers;

    private static void calculateKilometersForEachLine(LinkedList<Line> lineList) {
        for (Line line : lineList) {
            List<Double> kilometers1 = line.getKilometers();
            if (kilometers1.size() < 2) {
                continue;
            }
            List<Double> doubles = Stream.concat(
                            Stream.of(kilometers1.get(0)),
                            Stream.of(kilometers1.get(kilometers1.size() - 1)))
                    .toList();
            if (doubles.get(0) > doubles.get(1)) {
                line.setSize(doubles.get(0) - doubles.get(1));
            } else {
                line.setSize(doubles.get(1) - doubles.get(0));
            }
        }
    }

    public RoadStats calculateKilometers() {
        LinkedList<Line> lineList = calculateKilometers.createLinesAndAddToLineList(lastKilometer);

        calculateKilometersForEachLine(lineList);

        lineList.removeAll(Collections.singleton(null));
        RoadStats roadStats = new RoadStats(lineList);
        roadStats.setHowManyKilometers(lineList.stream().map(Line::getSize).filter(Objects::nonNull).reduce(Double::sum).orElse(0.0));
        return roadStats;
    }
}
