package org.pl.deenes.infrastructure.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pl.deenes.infrastructure.entity.AnalyseEntity;
import org.pl.deenes.infrastructure.entity.TrainStatsEntity;
import org.pl.deenes.model.Analyse;
import org.pl.deenes.model.TrainStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        AnalyseMapperImpl.class
})
class AnalyseDTOMapperImplTest {
    @Autowired
    private AnalyseMapper analyseMa;

    @Test
    @DisplayName("train to train DTO")
    void shouldMapTrainToTrainDTO() {
        Analyse asd = Analyse.builder()
                .startStation("asd")
                .trainType("TME")
                .trainStats(TrainStats.builder().firstKilometer(1.1).build())
                .build();

        AnalyseEntity analyseEntity = analyseMa.mapToEntity(asd);

        Assertions.assertNotNull(analyseEntity);
        Assertions.assertEquals("asd", analyseEntity.getStartStation());
        Assertions.assertEquals("TME", analyseEntity.getTrainType());
        Assertions.assertEquals(1.1, analyseEntity.getTrainStatsEntity().getFirstKilometer());
    }

    @Test
    @DisplayName("train DTO to train")
    void shouldMapTrainDTOToTrain() {
        AnalyseEntity asd = AnalyseEntity.builder()
                .startStation("asd")
                .trainType("TME")
                .trainStatsEntity(TrainStatsEntity.builder().firstKilometer(1.1).build())
                .build();

        Analyse analyse = analyseMa.mapFromEntity(asd);

        Assertions.assertNotNull(analyse);
        Assertions.assertEquals("asd", analyse.getStartStation());
        Assertions.assertEquals("TME", analyse.getTrainType());
        Assertions.assertEquals(1.1, analyse.getTrainStats().getFirstKilometer());
    }
}