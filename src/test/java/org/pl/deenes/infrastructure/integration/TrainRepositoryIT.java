package org.pl.deenes.infrastructure.integration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pl.deenes.infrastructure.repositories.dao.TrainDAO;
import org.pl.deenes.model.LocomotiveType;
import org.pl.deenes.model.Train;
import org.pl.deenes.model.TrainStats;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
class TrainRepositoryIT extends IntegrationReposIT {

    private final TrainDAO trainRepository;

    @Test
    void shouldSaveInDatabase() {
        Train train = Train.builder()
                .trainKwr(123)
                .companyName("asd")
                .startStation("A")
                .endStation("B")
                .trainType("TME")
                .trainNumber(321)
                .locomotiveType(LocomotiveType.ET22)
                .trainStats(List.of(TrainStats.builder().build(),
                        TrainStats.builder().howManyKilometers(1.1).build()))
                .build();

        Train save = trainRepository.save(train);
        Optional<Train> train1 = trainRepository.find(123);

        Assertions.assertEquals(2, save.getTrainStats().size());
        Assertions.assertNotNull(save.getTrainStats().get(0).getTrainStatsId());
        Assertions.assertEquals(1, save.getTrainStats().get(0).getTrainStatsId());
        Assertions.assertNotNull(save.getTrainId());
        Assertions.assertNotNull(save.getTrainStats().get(0).getTrainStatsId());

        Assertions.assertNotNull(train1.get().getTrainId());
        Assertions.assertNotNull(train1.get().getTrainStats().get(0).getTrainStatsId());

        log.warn(train1.get().toString());

    }


}
