package org.pl.deenes.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pl.deenes.model.Line;
import org.pl.deenes.model.TrainStats;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainStatsServiceImplTest {
    private RoadStatsService roadStatsService;

    @Mock
    private CalculateKilometersServiceImpl calculateKilometersService;

    @BeforeEach
    public void setup() {
        //MockitoAnnotations.openMocks(this);
        roadStatsService = new RoadStatsServiceImpl(10.0, calculateKilometersService);
    }

    @Test
    void testCalculateKilometers() {
        LinkedList<Line> lineList = new LinkedList<>();
        lineList.add(new Line(1, new LinkedList<>(List.of(0.0, 10.0))));
        lineList.add(new Line(2, new LinkedList<>(List.of(0.0, 5.0, 10.0))));
        lineList.add(new Line(3, new LinkedList<>(List.of(0.0, 20.0))));
        when(calculateKilometersService.createLinesAndAddToLineList(10.0)).thenReturn(lineList);

        TrainStats expectedTrainStats = new TrainStats(lineList);
        expectedTrainStats.setHowManyKilometers(40.0);

        TrainStats actualTrainStats = roadStatsService.calculateKilometers(10.0);

        Assertions.assertEquals(expectedTrainStats, actualTrainStats);

    }

    @Test
    void testCalculateKilometersForEmptyLineList() {
        LinkedList<Line> lineList = new LinkedList<>();
        when(calculateKilometersService.createLinesAndAddToLineList(10.0)).thenReturn(lineList);

        TrainStats expectedTrainStats = new TrainStats(lineList);
        expectedTrainStats.setHowManyKilometers(0.0);

        TrainStats actualTrainStats = roadStatsService.calculateKilometers(10.0);

        Assertions.assertEquals(expectedTrainStats, actualTrainStats);
    }


}