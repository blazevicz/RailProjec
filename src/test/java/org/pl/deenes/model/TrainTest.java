package org.pl.deenes.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrainTest {

    @Test
    void testEqualsAndHashCode() {
        Train train1 = Train.builder()
                .trainKwr(123)
                .build();

        Train train2 = Train.builder()
                .trainKwr(123)
                .build();

        assertEquals(train1, train2);
        assertEquals(train1.hashCode(), train2.hashCode());
    }

    @Test
    void testToString() {
        Train train = Train.builder()
                .trainKwr(123)
                .companyName("Company")
                .datePlan(LocalDate.of(2023, 8, 18))
                .build();

        String expectedToString = "Train(trainId=null, trainKwr=123, companyName=Company, datePlan=2023-08-18, roadStats=null, analyse=null, trainMaxWeight=null, trainMaxLength=null, startStation=null, endStation=null, trainType=null, locomotiveType=null, trainMaxSpeed=null, brakePercent=null, trainStats=null, trainNumber=null)";
        assertEquals(expectedToString, train.toString());
    }

    @Test
    void testWith() {
        Train train = Train.builder()
                .trainKwr(123)
                .build();

        Train updatedTrain = train.withTrainKwr(456);

        assertEquals(456, updatedTrain.getTrainKwr());
        assertEquals(train.getCompanyName(), updatedTrain.getCompanyName());
        assertEquals(train.getDatePlan(), updatedTrain.getDatePlan());
    }

    @Test
    void testBuilder() {
        Train train = Train.builder()
                .trainKwr(123)
                .companyName("Company")
                .build();

        assertEquals(123, train.getTrainKwr());
        assertEquals("Company", train.getCompanyName());
    }

    @Test
    void testSetTrainStats() {
        Train train = Train.builder()
                .trainKwr(123)
                .build();

        Set<TrainStats> trainStats = new HashSet<>();
        TrainStats stats1 = TrainStats.builder().build();
        TrainStats stats2 = TrainStats.builder().build();
        trainStats.add(stats1);
        trainStats.add(stats2);

        train.setTrainStats(trainStats);

        assertEquals(trainStats, train.getTrainStats());
    }
}
