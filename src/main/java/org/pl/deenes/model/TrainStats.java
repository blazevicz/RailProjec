package org.pl.deenes.model;

import lombok.*;

import java.util.LinkedList;

@Data
@Builder
@With
@Setter
@Getter
@ToString(of = {"trainStatsId", "howManyKilometers", "lineNumber"})
public class TrainStats {
    private final LinkedList<Line> lineList;
    private Integer trainStatsId;
    private Double howManyKilometers;
    // private Analyse analyse;
    // private Line line;
    private Integer lineNumber;
    private Double firstKilometer;
    private Double lastKilometer;
    private Train train;
    private Analyse analyse;
    //private Map<Integer, List<Double>> lineWithFirstLastKm;
    //private Train train;

}
