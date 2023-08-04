package org.pl.deenes.infrastructure.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrainEntityTest {
    @Test
    void testGetterAndSetter() {
        TrainEntity trainEntity = new TrainEntity();
        int trainId = 1;
        String companyName = "PKP CARGO S.A. Południowy Zakład Spółki";
        int trainKwr = 666401;
        LocalDate datePlan = LocalDate.of(2023, 3, 12);
        double roadStats = 139.528;
        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setDriverId(1); // Przykładowe ID kierowcy
        AnalyseEntity analyseEntity = new AnalyseEntity();
        analyseEntity.setTrainAnalyseId(1);

        trainEntity.setTrainId(trainId);
        trainEntity.setCompanyName(companyName);
        trainEntity.setTrainKwr(trainKwr);
        trainEntity.setDatePlan(datePlan);
        trainEntity.setRoadStats(roadStats);
        trainEntity.setDriver(driverEntity);
        trainEntity.setAnalyse(analyseEntity);

        assertEquals(trainId, trainEntity.getTrainId());
        assertEquals(companyName, trainEntity.getCompanyName());
        assertEquals(trainKwr, trainEntity.getTrainKwr());
        assertEquals(datePlan, trainEntity.getDatePlan());
        assertEquals(roadStats, trainEntity.getRoadStats());
        assertEquals(driverEntity, trainEntity.getDriver());
        assertEquals(analyseEntity, trainEntity.getAnalyse());
    }

    @Test
    void testEquals() {
        TrainEntity trainEntity1 = TrainEntity.builder()
                .trainId(1)
                .companyName("PKP CARGO S.A. Południowy Zakład Spółki")
                .trainKwr(666401)
                .datePlan(LocalDate.of(2023, 3, 12))
                .roadStats(139.528)
                .driver(new DriverEntity())
                .analyse(new AnalyseEntity())
                .build();

        TrainEntity trainEntity2 = TrainEntity.builder()
                .trainId(1)
                .companyName("PKP CARGO S.A. Południowy Zakład Spółki")
                .trainKwr(666401)
                .datePlan(LocalDate.of(2023, 3, 12))
                .roadStats(139.528)
                .driver(new DriverEntity())
                .analyse(new AnalyseEntity())
                .build();

        boolean expectedEquals = true;

        assertEquals(expectedEquals, trainEntity1.equals(trainEntity2));
    }
}