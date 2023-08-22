package org.pl.deenes.infrastructure.mapper;

import org.pl.deenes.infrastructure.entity.DriverEntity;
import org.pl.deenes.infrastructure.entity.TrainEntity;
import org.pl.deenes.infrastructure.entity.TrainStatsEntity;
import org.pl.deenes.model.Driver;
import org.pl.deenes.model.LocomotiveType;
import org.pl.deenes.model.Train;
import org.pl.deenes.model.TrainStats;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class TrainMapperImpl implements TrainMapper {

    @Override
    public TrainEntity mapToEntity(Train lineDetails) {
        if (lineDetails == null) {
            return null;
        }

        TrainEntity.TrainEntityBuilder trainEntity = TrainEntity.builder();

        trainEntity.trainId(lineDetails.getTrainId());
        trainEntity.companyName(lineDetails.getCompanyName());
        trainEntity.trainKwr(lineDetails.getTrainKwr());
        trainEntity.datePlan(lineDetails.getDatePlan());
        trainEntity.roadStats(lineDetails.getRoadStats());
        trainEntity.driver(driverToDriverEntity(lineDetails.getDriver()));
        trainEntity.trainNumber(lineDetails.getTrainNumber());
        trainEntity.trainMaxWeight(lineDetails.getTrainMaxWeight());
        trainEntity.trainMaxLength(lineDetails.getTrainMaxLength());
        trainEntity.startStation(lineDetails.getStartStation());
        trainEntity.endStation(lineDetails.getEndStation());
        trainEntity.trainType(lineDetails.getTrainType());
        if (lineDetails.getLocomotiveType() != null) {
            trainEntity.locomotiveType(lineDetails.getLocomotiveType().name());
        }
        trainEntity.trainMaxSpeed(lineDetails.getTrainMaxSpeed());
        trainEntity.brakePercent(lineDetails.getBrakePercent());
        trainEntity.trainStats(trainStatsSetToTrainStatsEntitySet(lineDetails.getTrainStats()));

        return trainEntity.build();
    }

    @Override
    public Train mapFromEntity(TrainEntity entity) {
        if (entity == null) {
            return null;
        }

        Train.TrainBuilder train = Train.builder();

        train.trainId(entity.getTrainId());
        train.trainKwr(entity.getTrainKwr());
        train.companyName(entity.getCompanyName());
        train.datePlan(entity.getDatePlan());
        train.roadStats(entity.getRoadStats());
        train.driver(driverEntityToDriver(entity.getDriver()));
        train.trainMaxWeight(entity.getTrainMaxWeight());
        train.trainMaxLength(entity.getTrainMaxLength());
        train.startStation(entity.getStartStation());
        train.endStation(entity.getEndStation());
        train.trainType(entity.getTrainType());
        if (entity.getLocomotiveType() != null) {
            train.locomotiveType(Enum.valueOf(LocomotiveType.class, entity.getLocomotiveType()));
        }
        train.trainMaxSpeed(entity.getTrainMaxSpeed());
        train.brakePercent(entity.getBrakePercent());
        train.trainStats(trainStatsEntitySetToTrainStatsSet(entity.getTrainStats()));
        train.trainNumber(entity.getTrainNumber());

        return train.build();
    }

    protected DriverEntity driverToDriverEntity(Driver driver) {
        if (driver == null) {
            return null;
        }

        DriverEntity.DriverEntityBuilder driverEntity = DriverEntity.builder();

        driverEntity.driverId(driver.getDriverId());
        driverEntity.name(driver.getName());
        driverEntity.surname(driver.getSurname());
        driverEntity.pesel(driver.getPesel());

        return driverEntity.build();
    }

    protected TrainStatsEntity trainStatsToTrainStatsEntity(TrainStats trainStats) {
        if (trainStats == null) {
            return null;
        }

        TrainStatsEntity.TrainStatsEntityBuilder trainStatsEntity = TrainStatsEntity.builder();

        trainStatsEntity.trainStatsId(trainStats.getTrainStatsId());
        trainStatsEntity.firstKilometer(trainStats.getFirstKilometer());
        trainStatsEntity.lastKilometer(trainStats.getLastKilometer());
        trainStatsEntity.lineNumber(trainStats.getLineNumber());
        trainStatsEntity.station(trainStats.getStation());

        return trainStatsEntity.build();
    }

    protected Set<TrainStatsEntity> trainStatsSetToTrainStatsEntitySet(Set<TrainStats> set) {
        if (set == null) {
            return Set.of();
        }

        Set<TrainStatsEntity> set1 = new LinkedHashSet<>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (TrainStats trainStats : set) {
            set1.add(trainStatsToTrainStatsEntity(trainStats));
        }

        return set1;
    }

    protected Driver driverEntityToDriver(DriverEntity driverEntity) {
        if (driverEntity == null) {
            return null;
        }

        Driver.DriverBuilder driver = Driver.builder();

        driver.driverId(driverEntity.getDriverId());
        driver.name(driverEntity.getName());
        driver.surname(driverEntity.getSurname());
        driver.pesel(driverEntity.getPesel());

        return driver.build();
    }

    protected TrainStats trainStatsEntityToTrainStats(TrainStatsEntity trainStatsEntity) {
        if (trainStatsEntity == null) {
            return null;
        }

        TrainStats.TrainStatsBuilder trainStats = TrainStats.builder();

        trainStats.trainStatsId(trainStatsEntity.getTrainStatsId());
        trainStats.lineNumber(trainStatsEntity.getLineNumber());
        trainStats.firstKilometer(trainStatsEntity.getFirstKilometer());
        trainStats.lastKilometer(trainStatsEntity.getLastKilometer());
        trainStats.station(trainStatsEntity.getStation());

        return trainStats.build();
    }

    protected Set<TrainStats> trainStatsEntitySetToTrainStatsSet(Set<TrainStatsEntity> set) {
        if (set == null) {
            return Set.of();
        }

        Set<TrainStats> set1 = new LinkedHashSet<>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (TrainStatsEntity trainStatsEntity : set) {
            set1.add(trainStatsEntityToTrainStats(trainStatsEntity));
        }

        return set1;
    }
}

