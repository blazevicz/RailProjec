package org.pl.deenes.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Analyse {

     Integer trainNumber;
     Integer trainKwr;
     Integer trainMaxWeight;
     Integer trainMaxLength;

     String startStation;
     String endStation;

     String trainType;
     LocomotiveType locomotiveType;

     Integer trainMaxSpeed;
     Integer brakePercent;

}
