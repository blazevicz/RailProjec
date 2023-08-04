package org.pl.deenes.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@With
@Setter
@Getter
@EqualsAndHashCode(of = "trainKwr")
public class Analyse {

    Integer trainAnalyseId;
    Integer trainKwr;
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
    Train trainEntity;

    public void addStat(TrainStats stat) {
        stat.setAnalyse(this);
        if (trainStats == null) {
            trainStats = new ArrayList<>();
        }
        trainStats.add(stat);
    }

}
