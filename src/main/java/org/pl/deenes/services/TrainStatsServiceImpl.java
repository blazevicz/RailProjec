package org.pl.deenes.services;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.model.Line;
import org.pl.deenes.model.TrainStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class TrainStatsServiceImpl implements TrainStatsService {
    private Double lastKilometer;
    @Autowired
    private CalculateKilometersService calculateKilometers;

    public TrainStatsServiceImpl(@Qualifier("lastKilometer") Double lastKilometer, CalculateKilometersServiceImpl calculateKilometers) {
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
    public TrainStats calculateKilometers(Double lastKilometer) {
        LinkedList<Line> lineList = calculateKilometers.createLinesAndAddToLineList(lastKilometer);
        calculateKilometersForEachLine(lineList);
        mapWithLineNumberAndFirstLastKilometr(lineList);
        // TrainStats trainStats = new TrainStats(lineList);
        var trainStatsBuilder = TrainStats.builder().lineList(lineList).build();
        //TrainStats trainStats = new TrainStats(mapWithLineNumberAndFirstLastKilometr(lineList));
        trainStatsBuilder.setHowManyKilometers(lineList.stream().map(Line::getSize).filter(Objects::nonNull).reduce(Double::sum).orElse(0.0));
        return trainStatsBuilder;
    }

    public Map<Integer, List<Double>> mapWithLineNumberAndFirstLastKilometr(LinkedList<Line> lineList) {
        return lineList.stream()
                .collect(Collectors.toMap(
                        Line::getLineNumber,
                        line -> {
                            Optional<Double> max = line.getKilometers().stream().max(Double::compareTo);
                            Optional<Double> min = line.getKilometers().stream().min(Double::compareTo);
                            return Arrays.asList(min.orElse(0.0), max.orElse(0.0));
                        }
                ));
    }
}
