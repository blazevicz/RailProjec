package org.pl.deenes.services;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.model.Line;
import org.pl.deenes.model.TrainStats;
import org.pl.deenes.services.interfaces.CalculateKilometersService;
import org.pl.deenes.services.interfaces.TrainStatsService;
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

    private static void sumKilometersForEachLine(Line line, @NonNull List<Double> kilometers1) {
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

    void calculateKilometersForEachLine(@NonNull List<Line> lineList) {
        for (Line line : lineList) {
            List<Double> kilometers1 = new ArrayList<>(line.getKilometers());
            Collections.sort(kilometers1);
            if (kilometers1.size() < 2) {
                continue;
            }
            sumKilometersForEachLine(line, kilometers1);
        }
    }

    @Override
    public TrainStats calculateKilometers(Double lastKilometer) {
        LinkedList<Line> lineList = calculateKilometers.createLinesAndAddToLineList(lastKilometer);
        calculateKilometersForEachLine(lineList);
        mapWithLineNumberAndFirstLastKilometer(lineList);
        var trainStatsBuilder = TrainStats.builder().lineList(lineList).build();
        trainStatsBuilder.setHowManyKilometers(lineList.stream().map(Line::getSize).filter(Objects::nonNull).reduce(Double::sum).orElse(0.0));
        return trainStatsBuilder;
    }

    public Map<Integer, List<Double>> mapWithLineNumberAndFirstLastKilometer(@NonNull List<Line> lineList) {
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
