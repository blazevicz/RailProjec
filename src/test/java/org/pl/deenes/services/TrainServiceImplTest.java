package org.pl.deenes.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pl.deenes.infrastructure.repositories.dao.TrainDAO;
import org.pl.deenes.model.Analyse;
import org.pl.deenes.model.Line;
import org.pl.deenes.model.Train;
import org.pl.deenes.model.TrainStats;
import org.pl.deenes.services.interfaces.AnalyseService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainServiceImplTest {
    @Mock
    private TimetableImpl readKilometersServiceImpl;
    @Mock
    private TrainStatsServiceImpl trainStatsServiceImpl;
    @Mock
    private AnalyseService analyseServiceImpl;
    @Mock
    private TrainDAO trainDAO;
    @Mock
    private CalculateKilometersServiceImpl calculateKilometers;
    @InjectMocks
    private TrainServiceImpl trainService;


    @Test
    void testTrainCreate() {
        String companyName = "Company Name\nSample Company\nDate: dnia 21.07.2023\n...";
        var split = Arrays.asList(companyName.split("\n"));
        LocalDate localDate = LocalDate.of(2023, 7, 21);
        TrainStats trainStats = TrainStats.builder()
                .lineList(new LinkedList<>(List.of(new Line(1, List.of(1.0, 55.5)), new Line(2, List.of(50.0, 55.5)))))
                .howManyKilometers(1000.0)
                .build();
        Analyse analyse = Analyse.builder()
                .trainKwr(123456)
                .build();

        doNothing().when(calculateKilometers).setTimetableDetails(any(TimetableDetails.class));
        when(readKilometersServiceImpl.getCompanyName()).thenReturn(companyName);
        when(readKilometersServiceImpl.read()).thenReturn(new TimetableDetails());
        when(trainStatsServiceImpl.getLastKilometer()).thenReturn(500.0);
        when(trainStatsServiceImpl.calculateKilometers(500.0)).thenReturn(trainStats);
        when(analyseServiceImpl.creatingTrainAnalyse(trainStats)).thenReturn(analyse);


        Train train = trainService.trainCreate();

        assertThat(train.getCompanyName()).isEqualTo(split.get(2));
        assertThat(train.getTrainKwr()).isEqualTo(123456);
        assertThat(train.getDatePlan()).isEqualTo(localDate);
        assertThat(train.getRoadStats()).isEqualTo(1000.0);
        assertThat(train.getAnalyse()).isEqualTo(analyse);
    }

    @Test
    void testSaveTrain() {
        Train train = Train.builder()
                .companyName("Sample Company")
                .trainKwr(123456)
                .datePlan(LocalDate.of(2023, 7, 21))
                .roadStats(1000.0)
                .build();

        when(trainDAO.save(any(Train.class))).thenReturn(train);

        Train savedTrain = trainService.saveTrain(train);

        assertThat(savedTrain).isEqualTo(train);
    }

    @Test
    void testFindTrain() {
        int kwr = 123456;
        Train train = Train.builder()
                .companyName("Sample Company")
                .trainKwr(kwr)
                .datePlan(LocalDate.of(2023, 7, 21))
                .roadStats(1000.0)
                .build();

        when(trainDAO.find(kwr)).thenReturn(Optional.of(train));

        Train foundTrain = trainService.findTrain(kwr);

        assertThat(foundTrain).isEqualTo(train);
    }
}