package org.pl.deenes.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pl.deenes.infrastructure.repositories.dao.AnalyseDAO;
import org.pl.deenes.infrastructure.repositories.dao.TrainDAO;
import org.pl.deenes.model.Analyse;
import org.pl.deenes.model.Train;
import org.pl.deenes.model.TrainStats;
import org.pl.deenes.services.interfaces.AnalyseService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainServiceImplTest {

    @Mock
    private TimetableImpl readKilometersService;
    @Mock
    private TrainStatsServiceImpl trainStatsService;
    @Mock
    private CalculateKilometersServiceImpl calculateKilometers;
    @Mock
    private AnalyseService analyseService;
    @Mock
    private TrainDAO trainDAO;
    @Mock
    private AnalyseDAO analyseDAO;
    @InjectMocks
    private TrainServiceImpl trainService;

    @Test
    void trainCreate_ShouldReturnCreatedTrain() {
        String link = "src/main/resources/IDDE4 Dodatek 1 IZ Sosnowiec z popr 14.pdf";
        when(readKilometersService.read(link)).thenReturn(TimetableDetails.builder().build());
        when(readKilometersService.getCompanyName()).thenReturn("Company Name\nSecond Line\nThird Line");
        when(trainStatsService.calculateKilometers(any())).thenReturn(TrainStats.builder().build());
        when(analyseService.creatingTrainAnalyse(any(), any())).thenReturn(Analyse.builder().build());

        Train createdTrain = trainService.trainCreate(link);

        verify(readKilometersService).read(link);
        verify(readKilometersService).getCompanyName();
        verify(trainStatsService).calculateKilometers(any());
        verify(analyseService).creatingTrainAnalyse(any(), any());
        verify(trainDAO).save(any());
        assertEquals(3, createdTrain.getTrainStats().size());
    }

    @Test
    void saveTrain_ShouldSaveTrainAndReturnSavedInstance() {
        Train train = Train.builder().build();
        when(trainDAO.save(train)).thenReturn(train);

        Train savedTrain = trainService.saveTrain(train);

        verify(trainDAO).save(train);
        assertEquals(train, savedTrain);
    }

    @Test
    void findTrain_ExistingTrain_ShouldReturnOptionalWithTrain() {
        Train expectedTrain = Train.builder().build();
        when(trainDAO.find(anyInt())).thenReturn(Optional.of(expectedTrain));

        Optional<Train> foundTrain = trainService.findTrain(123);

        verify(trainDAO).find(123);
        assertEquals(expectedTrain, foundTrain.orElse(null));
    }

    @Test
    void findTrain_NonExistingTrain_ShouldReturnEmptyOptional() {
        when(trainDAO.find(anyInt())).thenReturn(Optional.empty());

        Optional<Train> foundTrain = trainService.findTrain(123);

        verify(trainDAO).find(123);
        assertEquals(Optional.empty(), foundTrain);
    }
}
