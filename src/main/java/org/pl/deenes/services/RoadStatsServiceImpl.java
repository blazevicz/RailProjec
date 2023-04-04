package org.pl.deenes.services;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.data.Line;
import org.pl.deenes.data.RoadStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@Slf4j
@Getter
@Setter
@NoArgsConstructor

public class RoadStatsServiceImpl implements RoadStatsService {
    private Double lastKilometer;
    @Autowired

    private CalculateKilometersService calculateKilometers;

    public RoadStatsServiceImpl(@Qualifier("lastKilometer") Double lastKilometer, CalculateKilometersServiceImpl calculateKilometers) {
        this.lastKilometer = lastKilometer;
        this.calculateKilometers = calculateKilometers;
    }

    private static void calculateKilometersForEachLine(LinkedList<Line> lineList) {
        for (Line line : lineList) {
            List<Double> kilometers1 = line.getKilometers();
            Collections.sort(kilometers1);
            if (kilometers1.size() < 2) {
                continue;
            }
            sumKilometersForEachLine(line, kilometers1);
        }
    }

    private static void sumKilometersForEachLine(Line line, List<Double> kilometers1) {
        List<Double> allKilometersInLine = Stream.concat(
                        Stream.of(kilometers1.get(0)),
                        Stream.of(kilometers1.get(kilometers1.size() - 1)))
                .toList();
        if (allKilometersInLine.get(0) > allKilometersInLine.get(1)) {
            line.setSize(allKilometersInLine.get(0) - allKilometersInLine.get(1));
        } else {
            line.setSize(allKilometersInLine.get(1) - allKilometersInLine.get(0));
        }
    }

    @Override
    public RoadStats calculateKilometers(Double lastKilometer) {
        LinkedList<Line> lineList = calculateKilometers.createLinesAndAddToLineList(lastKilometer);
        calculateKilometersForEachLine(lineList);
        RoadStats roadStats = new RoadStats(lineList);
        roadStats.setHowManyKilometers(lineList.stream().map(Line::getSize).filter(Objects::nonNull).reduce(Double::sum).orElse(0.0));
        return roadStats;
    }
}
