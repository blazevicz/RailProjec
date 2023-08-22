package org.pl.deenes.infrastructure.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pl.deenes.infrastructure.entity.TrainEntity;
import org.pl.deenes.infrastructure.entity.TrainStatsEntity;
import org.pl.deenes.model.LocomotiveType;
import org.pl.deenes.model.Train;
import org.pl.deenes.model.TrainStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        TrainMapperImpl.class
})
@Slf4j
class TrainEntityMapperImplTest {
    @Autowired
    private TrainMapper trainEntityMapper;

    @Test
    void shouldMapTrainToTrainEntity() {
        Train train = Train.builder()
                .trainKwr(123)
                .companyName("asd")
                .startStation("A")
                .endStation("B")
                .trainType("TME")
                .trainNumber(321)
                .locomotiveType(LocomotiveType.ET22)
                .trainStats(Set.of(TrainStats.builder().lineNumber(2).build(),
                        TrainStats.builder().howManyKilometers(1.1).build()))
                .build();


        train.getTrainStats().forEach(a -> a.setTrainEntity(train));
        TrainEntity trainEntity = trainEntityMapper.mapToEntity(train);

        Assertions.assertNotNull(trainEntity);
        Assertions.assertEquals(train.getCompanyName(), trainEntity.getCompanyName());
        Assertions.assertEquals(train.getRoadStats(), trainEntity.getRoadStats());
        Assertions.assertEquals(train.getTrainKwr(), trainEntity.getTrainKwr());
    }

    @Test
    void shouldMapTrainEntityToTrain() {
        TrainEntity trainEntity = TrainEntity.builder()
                .companyName("TEST")
                .roadStats(777.11)
                .trainKwr(123)
                .trainStats(Set.of(TrainStatsEntity.builder().build()))
                .build();

        Train train = trainEntityMapper.mapFromEntity(trainEntity);

        Assertions.assertNotNull(train);
        Assertions.assertEquals("TEST", train.getCompanyName());
        Assertions.assertEquals(777.11, train.getRoadStats());
        Assertions.assertEquals(123, train.getTrainKwr());
        Assertions.assertEquals(1, train.getTrainStats().size());

    }
}