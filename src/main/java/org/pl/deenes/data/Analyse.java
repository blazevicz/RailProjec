package org.pl.deenes.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@AllArgsConstructor
@Builder
@ToString
public class Analyse {

    private int trainNumber;
    private int trainKwr;
    private int trainMaxWeight;
    private int trainMaxLength;

    private String startStation;
    private String endStation;

    private String trainType;
    private String locomotiveType;

    private int trainMaxSpeed;
    private int brakePercent;


}
