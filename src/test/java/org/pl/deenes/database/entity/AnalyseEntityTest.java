package org.pl.deenes.database.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pl.deenes.infrastructure.entity.AnalyseEntity;
import org.pl.deenes.infrastructure.entity.TrainStatsEntity;

class AnalyseEntityTest {
    @Mock
    TrainStatsEntity roadStats;
    @InjectMocks
    AnalyseEntity analyseEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testSetTrainNumber() {
        analyseEntity.setTrainNumber(0);
    }

    @Test
    void testSetTrainKwr() {
        analyseEntity.setTrainKwr(0);
    }

    @Test
    void testSetTrainMaxWeight() {
        analyseEntity.setTrainMaxWeight(0);
    }

    @Test
    void testSetTrainMaxLength() {
        analyseEntity.setTrainMaxLength(0);
    }

    @Test
    void testSetStartStation() {
        analyseEntity.setStartStation("startStation");
    }

    @Test
    void testSetEndStation() {
        analyseEntity.setEndStation("endStation");
    }

    @Test
    void testSetTrainType() {
        analyseEntity.setTrainType("trainType");
    }

    @Test
    void testSetLocomotiveType() {
        analyseEntity.setLocomotiveType("locomotiveType");
    }

    @Test
    void testSetTrainMaxSpeed() {
        analyseEntity.setTrainMaxSpeed(0);
    }

    @Test
    void testSetBrakePercent() {
        analyseEntity.setBrakePercent(0);
    }


    @Test
    void testBuilder() {
        AnalyseEntity.AnalyseEntityBuilder result = AnalyseEntity.builder();
        Assertions.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme