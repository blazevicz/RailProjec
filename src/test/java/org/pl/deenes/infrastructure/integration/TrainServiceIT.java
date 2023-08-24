package org.pl.deenes.infrastructure.integration;


import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.pl.deenes.model.Analyse;
import org.pl.deenes.model.Train;
import org.pl.deenes.model.TrainStats;
import org.pl.deenes.services.TimetableDetails;
import org.pl.deenes.services.TimetableImpl;
import org.pl.deenes.services.TrainServiceImpl;
import org.pl.deenes.services.TrainStatsServiceImpl;
import org.pl.deenes.services.interfaces.AnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@SpringBootTest
@AllArgsConstructor(onConstructor = @__(@Autowired))
class TrainServiceIT {

    @MockBean
    private TimetableImpl readKilometersService;

    @MockBean
    private TrainStatsServiceImpl trainStatsService;

    @MockBean
    private AnalyseService analyseService;

    private TrainServiceImpl trainService;

    @Test
    void shouldCreateTrain() {
        String link = "src/test/resources/RJ_SKRJ_666401_464028_9.pdf";
        TimetableDetails timetableDetails = TimetableDetails.builder()
                .allKilometers(List.of(List.of("1.1", "2.2")))
                .lineNumbers(new ArrayList<>(List.of(2)))
                .stations(new ArrayList<>(List.of("Bytom")))
                .kilometersAfterConvert(new HashSet<>(Set.of(1, 2)))
                .build();
        when(readKilometersService.read(link)).thenReturn(timetableDetails);
        when(readKilometersService.getCompanyName()).thenReturn("PKP CARGO S.A. Południowy Zakład Spółki\n" +
                "na wniosek nr E23-POLUDNIO-4118128 z dnia 12.03.2023\n" +
                "zarządzenie IRJ_2023_666401 z dnia 12.03.2023");
        when(analyseService.creatingTrainAnalyse(any(), anyList()))
                .thenReturn(Analyse.builder()
                        .trainStats(List.of(TrainStats.builder().build()))
                        .build());
        when(trainStatsService.calculateKilometers(any())).thenReturn(TrainStats.builder().build());

        Train result = trainService.trainCreate(link);

        assertNotNull(result);
    }
}
