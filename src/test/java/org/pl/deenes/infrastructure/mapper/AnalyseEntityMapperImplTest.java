package org.pl.deenes.infrastructure.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pl.deenes.infrastructure.entity.AnalyseEntity;
import org.pl.deenes.model.Analyse;
import org.pl.deenes.model.TrainStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        AnalyseMapperImpl.class
})
class AnalyseEntityMapperImplTest {
    @Autowired
    private AnalyseMapper analyseMa;

    @Test
    @DisplayName("train to train entity")
    void shouldMapTrainToTrainDTO() {
        Analyse asd = Analyse.builder()
                .startStation("asd")
                .trainType("TME")
                .trainStats(List.of(TrainStats.builder().build(), TrainStats.builder().build()))
                .build();

        AnalyseEntity analyseEntity = analyseMa.mapToEntity(asd);

        Assertions.assertNotNull(analyseEntity);
        Assertions.assertEquals("asd", analyseEntity.getStartStation());
        Assertions.assertEquals("TME", analyseEntity.getTrainType());
        Assertions.assertEquals(2, analyseEntity.getTrainStats().size());
    }

    @Test
    @DisplayName("train entity to train")
    void shouldMapTrainDTOToTrain() {
        AnalyseEntity asd = AnalyseEntity.builder()
                .startStation("asd")
                .trainType("TME")
                .trainStats(List.of())
                .build();

        Analyse analyse = analyseMa.mapFromEntity(asd);

        Assertions.assertNotNull(analyse);
        Assertions.assertEquals("asd", analyse.getStartStation());
        Assertions.assertEquals("TME", analyse.getTrainType());
        Assertions.assertEquals(2, analyse.getTrainStats().size());

    }
}