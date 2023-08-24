package org.pl.deenes.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pl.deenes.model.Analyse;
import org.pl.deenes.model.LocomotiveType;
import org.pl.deenes.model.TrainStats;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnalyseServiceImplTest {

    @Mock
    private TimetableImpl timetable;

    @Mock
    private TrainStatsServiceImpl trainStatsService;

    @InjectMocks
    private AnalyseServiceImpl analyseService;

    @Test
    void testCreatingTrainAnalyse() {
        TrainStats trainStats = TrainStats.builder()
                .lineNumber(1)
                .firstKilometer(10.0)
                .lastKilometer(50.0)
                .build();

        when(timetable.getTextToAnalyse()).thenReturn("TME 464028/9 (666401) Relacja Dąbrowa Górnicza Towarowa - Chorula Cementownia 2");
        when(timetable.getBruttoTextToAnalyse()).thenReturn(
                "Texxt text to analyse analyse analyse analyse analyse analyse analyse analyse ET22 100 120 50 200 TME 1234/1 5678 ");
        when(trainStatsService.mapWithLineNumberAndFirstLastKilometer(any())).thenReturn(Map.of(1, List.of(10.0, 50.0)));


        Analyse result = analyseService.creatingTrainAnalyse(trainStats, List.of());

        assertEquals(LocomotiveType.ET22, result.getLocomotiveType());
        assertEquals(100, result.getTrainMaxWeight());
        assertEquals(120, result.getTrainMaxSpeed());
        assertEquals(50, result.getBrakePercent());
        assertEquals(200, result.getTrainMaxLength());
        assertEquals("TME", result.getTrainType());
        assertEquals(464028, result.getTrainNumber());
        assertEquals(666401, result.getTrainKwr());
        assertEquals("Dąbrowa Górnicza Towarowa", result.getStartStation());
        assertEquals("Chorula Cementownia", result.getEndStation());
    }

    @Test
    void testTrainStatsCreator() {
        Map<Integer, List<Double>> mapWithLineNumberAndFirstLastKilometer = Map.of(1, List.of(10.0, 50.0));

        List<TrainStats> trainStatsList = analyseService.trainStatsCreator(mapWithLineNumberAndFirstLastKilometer, List.of());

        assertEquals(1, trainStatsList.size());

        TrainStats trainStats = trainStatsList.get(0);
        assertEquals(1, trainStats.getLineNumber());
        assertEquals(10.0, trainStats.getFirstKilometer());
        assertEquals(50.0, trainStats.getLastKilometer());
    }
}