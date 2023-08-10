package org.pl.deenes.model;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@With
@Data
@Builder
@Setter
@Getter
@ToString(exclude = {"driver"})
@EqualsAndHashCode(of = "trainId")
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
    List<TrainStats> trainStats;
    Integer trainNumber;

    public void addStat(@NonNull TrainStats stat) {
        stat.setTrain(this);
        if (trainStats == null) {
            trainStats = new ArrayList<>();
        }
        trainStats.add(stat);
    }
}

