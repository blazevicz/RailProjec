package org.pl.deenes.infrastructure.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pl.deenes.infrastructure.entity.TrainStatsEntity;
import org.pl.deenes.model.TrainStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        TrainStatsMapperImpl.class
})
class TrainStatsEntityMapperTest {

    @Autowired
    private TrainStatsMapper trainStatsMapper;

    @Test
    @DisplayName("stats to stats entity")
    void mapFromEntity() {
        var trainStatsBuilder = TrainStats.builder()
                .firstKilometer(1.1)
                .lastKilometer(2.2)
                .build();

        var trainStats = trainStatsMapper.mapToEntity(trainStatsBuilder);
        Assertions.assertNotNull(trainStats);
        Assertions.assertEquals(1.1, trainStatsBuilder.getFirstKilometer());
        Assertions.assertEquals(2.2, trainStatsBuilder.getLastKilometer());
    }

    @Test
    @DisplayName("stats DTO to entity")
    void mapToEntity() {
        var trainStatsBuilder = TrainStatsEntity.builder()
                .firstKilometer(1.1)
                .lastKilometer(2.2)
                .build();

        TrainStats trainStats = trainStatsMapper.mapFromEntity(trainStatsBuilder);
        Assertions.assertNotNull(trainStats);
        Assertions.assertEquals(1.1, trainStatsBuilder.getFirstKilometer());
        Assertions.assertEquals(2.2, trainStatsBuilder.getLastKilometer());
    }
}