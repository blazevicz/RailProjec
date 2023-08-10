package org.pl.deenes.infrastructure.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pl.deenes.infrastructure.entity.TrainEntity;
import org.pl.deenes.infrastructure.entity.TrainStatsEntity;
import org.pl.deenes.model.Train;
import org.pl.deenes.model.TrainStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        TrainMapperImpl.class
})
class TrainEntityMapperImplTest {
    @Autowired
    private TrainMapper trainEntityMapper;

    @Test
    void shouldMapTrainToTrainEntity() {
        Train train = Train.builder()
                .companyName("TEST")
                .roadStats(777.11)
                .trainStats(List.of(TrainStats.builder().build()))
                .build();

        TrainEntity trainEntity = trainEntityMapper.mapToEntity(train);

        Assertions.assertNotNull(trainEntity);
        Assertions.assertEquals(train.getCompanyName(), trainEntity.getCompanyName());
        Assertions.assertEquals(train.getRoadStats(), trainEntity.getRoadStats());
        Assertions.assertEquals(train.getTrainKwr(), trainEntity.getTrainKwr());
        Assertions.assertEquals(train.getTrainStats().size(), trainEntity.getTrainStats().size());
    }

    @Test
    void shouldMapTrainEntityToTrain() {
        TrainEntity trainEntity = TrainEntity.builder()
                .companyName("TEST")
                .roadStats(777.11)
                .trainKwr(123)
                .trainStats(List.of(TrainStatsEntity.builder().build()))
                .build();

        Train train = trainEntityMapper.mapFromEntity(trainEntity, new CycleAvoidingMappingContext());

        Assertions.assertNotNull(train);
        Assertions.assertEquals("TEST", train.getCompanyName());
        Assertions.assertEquals(777.11, train.getRoadStats());
        Assertions.assertEquals(123, train.getTrainKwr());
        Assertions.assertEquals(1, train.getTrainStats().size());

    }
}