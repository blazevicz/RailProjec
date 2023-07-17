package org.pl.deenes.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
@EqualsAndHashCode(of = "trainKwr")
public class Analyse {

    Integer trainKwr;
    Integer trainMaxWeight;
    Integer trainMaxLength;
    String startStation;
    String endStation;
    String trainType;
    LocomotiveType locomotiveType;
    Integer trainMaxSpeed;
    Integer brakePercent;
    TrainStats trainStats;
    Train train;
    Integer trainNumber;

}
