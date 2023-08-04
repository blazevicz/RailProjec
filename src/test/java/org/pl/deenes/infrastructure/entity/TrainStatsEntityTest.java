package org.pl.deenes.infrastructure.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrainStatsEntityTest {
    @Test
    void testGetterAndSetter() {
        TrainStatsEntity trainStatsEntity = new TrainStatsEntity();

        int trainStatsId = 1;
        double firstKilometer = 100.5;
        double lastKilometer = 200.5;
        int lineNumber = 123;
        AnalyseEntity analyseEntity = new AnalyseEntity();
        analyseEntity.setTrainAnalyseId(1);

        trainStatsEntity.setTrainStatsId(trainStatsId);
        trainStatsEntity.setFirstKilometer(firstKilometer);
        trainStatsEntity.setLastKilometer(lastKilometer);
        trainStatsEntity.setLineNumber(lineNumber);
        trainStatsEntity.setAnalyse(analyseEntity);

        assertEquals(trainStatsId, trainStatsEntity.getTrainStatsId());
        assertEquals(firstKilometer, trainStatsEntity.getFirstKilometer());
        assertEquals(lastKilometer, trainStatsEntity.getLastKilometer());
        assertEquals(lineNumber, trainStatsEntity.getLineNumber());
        assertEquals(analyseEntity, trainStatsEntity.getAnalyse());
    }

    @Test
    void testEquals() {
        TrainStatsEntity trainStatsEntity1 = TrainStatsEntity.builder()
                .trainStatsId(1)
                .firstKilometer(100.5)
                .lastKilometer(200.5)
                .lineNumber(123)
                .analyse(new AnalyseEntity())
                .build();

        TrainStatsEntity trainStatsEntity2 = TrainStatsEntity.builder()
                .trainStatsId(1)
                .firstKilometer(100.5)
                .lastKilometer(200.5)
                .lineNumber(123)
                .analyse(new AnalyseEntity())
                .build();

        boolean expectedEquals = true;

        assertEquals(expectedEquals, trainStatsEntity1.equals(trainStatsEntity2));
    }
}