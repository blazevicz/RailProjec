package org.pl.deenes.infrastructure.entity;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LineDetailsEntityTest {
    @Test
    void testGetterAndSetter() {
        LineDetailsEntity lineDetailsEntity = new LineDetailsEntity();

        int lineDetailsId = 1;
        String startStation = "Station A";
        String endStation = "Station B";
        int lineNumber = 123;
        double startKilometer = 10.5;
        double endKilometer = 25.8;
        int page = 1;
        int railwayRegion = 456;


        lineDetailsEntity.setLineDetailsId(lineDetailsId);
        lineDetailsEntity.setStartStation(startStation);
        lineDetailsEntity.setEndStation(endStation);
        lineDetailsEntity.setLineNumber(lineNumber);
        lineDetailsEntity.setStartKilometer(startKilometer);
        lineDetailsEntity.setEndKilometer(endKilometer);
        lineDetailsEntity.setPage(page);
        lineDetailsEntity.setRailwayRegion(railwayRegion);

        Assertions.assertEquals(lineDetailsId, lineDetailsEntity.getLineDetailsId());
        Assertions.assertEquals(startStation, lineDetailsEntity.getStartStation());
        Assertions.assertEquals(endStation, lineDetailsEntity.getEndStation());
        Assertions.assertEquals(lineNumber, lineDetailsEntity.getLineNumber());
        Assertions.assertEquals(startKilometer, lineDetailsEntity.getStartKilometer());
        Assertions.assertEquals(endKilometer, lineDetailsEntity.getEndKilometer());
        Assertions.assertEquals(page, lineDetailsEntity.getPage());
        Assertions.assertEquals(railwayRegion, lineDetailsEntity.getRailwayRegion());
    }

}
