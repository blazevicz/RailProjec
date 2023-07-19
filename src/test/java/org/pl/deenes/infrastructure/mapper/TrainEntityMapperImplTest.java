package org.pl.deenes.infrastructure.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pl.deenes.infrastructure.entity.AnalyseEntity;
import org.pl.deenes.infrastructure.entity.TrainEntity;
import org.pl.deenes.model.Analyse;
import org.pl.deenes.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        TrainMapperImpl.class
})
class TrainEntityMapperImplTest {
    @Autowired
    private TrainMapper trainEntityMapper;

    @Test
    void shouldMapTrainToTrainDTO() {
        Train train = Train.builder()
                .companyName("TEST")
                .roadStats(777.11)
                .analyse(Analyse.builder().trainKwr(123).build())
                .build();

        TrainEntity trainEntity = trainEntityMapper.mapToEntity(train);

        Assertions.assertNotNull(trainEntity);
        Assertions.assertEquals("TEST", trainEntity.getCompanyName());
        Assertions.assertEquals(777.11, trainEntity.getRoadStats());
        Assertions.assertEquals(123, trainEntity.getAnalyse().getTrainKwr());
    }

    @Test
    void shouldMapTrainDTOToTrain() {
        TrainEntity trainEntity = TrainEntity.builder()
                .companyName("TEST")
                .roadStats(777.11)
                .analyse(AnalyseEntity.builder().trainKwr(123).build())
                .build();

        Train train = trainEntityMapper.mapFromEntity(trainEntity);

        Assertions.assertNotNull(train);
        Assertions.assertEquals("TEST", train.getCompanyName());
        Assertions.assertEquals(777.11, train.getRoadStats());
        Assertions.assertEquals(123, train.getAnalyse().getTrainKwr());

    }
}