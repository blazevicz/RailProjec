package org.pl.deenes.infrastructure.integration;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pl.deenes.infrastructure.entity.TrainEntity;
import org.pl.deenes.infrastructure.entity.TrainStatsEntity;
import org.pl.deenes.infrastructure.mapper.TrainMapper;
import org.pl.deenes.infrastructure.mapper.TrainStatsMapper;
import org.pl.deenes.infrastructure.repositories.jpa.TrainJpaRepository;
import org.pl.deenes.model.LocomotiveType;
import org.pl.deenes.model.Train;
import org.pl.deenes.model.TrainStats;
import org.pl.deenes.services.ResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@AllArgsConstructor(onConstructor = @__(@Autowired))
class TrainRepositoryIT extends IntegrationReposIT {

    private final TrainJpaRepository trainRepository;
    private final ResultServiceImpl resultService;
    private final TrainMapper trainMapper;
    private final TrainStatsMapper trainStatsMapper;

    @Test
    void shouldSaveInDatabase() {
        var build1 =
                TrainStats.builder().lineNumber(1).build();
        var build2 =
                TrainStats.builder().lineNumber(2).build();

        var train = Train.builder()
                .trainKwr(123)
                .companyName("asd")
                .startStation("A")
                .trainMaxSpeed(1)
                .trainMaxWeight(2)
                .trainMaxLength(3)
                .endStation("B")
                .trainType("TME")
                .trainNumber(321)
                .locomotiveType(LocomotiveType.ET22)
                .brakePercent(2)
                .build();

        build1.setTrainEntity(train);
        build2.setTrainEntity(train);

        train.setTrainStats(Set.of(build1, build2));

        TrainEntity trainEntity = trainMapper.mapToEntity(train);
        TrainStatsEntity trainStatsEntity1 = trainStatsMapper.mapToEntity(build1);
        TrainStatsEntity trainStatsEntity2 = trainStatsMapper.mapToEntity(build2);

        trainStatsEntity1.setTrainEntity(trainEntity);
        trainStatsEntity2.setTrainEntity(trainEntity);

        trainEntity.setTrainStats(Set.of(trainStatsEntity1, trainStatsEntity2));

        trainRepository.save(trainEntity);
        var save = trainRepository.findAll();

        Assertions.assertEquals(1, save.size());
        Assertions.assertEquals(2, save.get(0).getTrainStats().size());
        Assertions.assertNotNull(save.get(0).getTrainId());
        Assertions.assertNotNull(save.get(0).getTrainId());
    }
}
