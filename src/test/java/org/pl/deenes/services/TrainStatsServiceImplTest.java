package org.pl.deenes.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pl.deenes.model.Line;
import org.pl.deenes.model.TrainStats;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainStatsServiceImplTest {
    @Mock
    private CalculateKilometersServiceImpl calculateKilometersService;

    @InjectMocks
    private TrainStatsServiceImpl trainStatsService;

    @Test
    void testCalculateKilometers() {
        TrainStatsServiceImpl trainStatsService = new TrainStatsServiceImpl(1000.0, calculateKilometersService);
        Line line1 = new Line(1, List.of(1.0, 55.5));
        Line line2 = new Line(2, List.of(50.0, 55.5));
        List<Line> lineList = new ArrayList<>((Arrays.asList(line1, line2)));

        double lastKilometer = 1000.0;

        LinkedList<Line> mockLineList = new LinkedList<>(List.of(line1, line2));
        when(calculateKilometersService.createLinesAndAddToLineList(lastKilometer)).thenReturn(mockLineList);


        trainStatsService.calculateKilometersForEachLine(lineList);

        assertThat(line1.getSize()).isEqualTo(54.5);
        assertThat(line2.getSize()).isEqualTo(5.5);


        TrainStats trainStats = trainStatsService.calculateKilometers(lastKilometer);


        assertThat(trainStats.getHowManyKilometers()).isEqualTo(60.0);
        assertThat(trainStats.getLineList()).isEqualTo(lineList);
    }

    @Test
    void testMapWithLineNumberAndFirstLastKilometer() {
        Line line1 = new Line(1, List.of(1.0, 55.5));
        Line line2 = new Line(2, List.of(50.0, 55.5));

        List<Line> lineList = Arrays.asList(line1, line2);

        Map<Integer, List<Double>> result = trainStatsService.mapWithLineNumberAndFirstLastKilometer(lineList);

        assertThat(result).containsOnlyKeys(1, 2);
        assertThat(result.get(1)).containsExactly(1.0, 55.5);
        assertThat(result.get(2)).containsExactly(50.0, 55.5);
    }
}