package org.pl.deenes.model;

import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@With
@Data
@Builder
@Setter
@Getter
@ToString(exclude = {"driver"})
@EqualsAndHashCode(of = {"trainKwr"})
public class Train {

    Integer trainId;
    Integer trainKwr;
    String companyName;
    LocalDate datePlan;
    Double roadStats;
    Analyse analyse;
    Driver driver;
    Integer trainMaxWeight;
    Integer trainMaxLength;
    String startStation;
    String endStation;
    String trainType;
    LocomotiveType locomotiveType;
    Integer trainMaxSpeed;
    Integer brakePercent;
    Set<TrainStats> trainStats;
    Integer trainNumber;

    public void addStat(@NonNull TrainStats stat) {
        stat.setTrainEntity(this);
        if (trainStats == null) {
            trainStats = new HashSet<>();
        }
        trainStats.add(stat);
    }
}

