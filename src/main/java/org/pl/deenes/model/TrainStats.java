package org.pl.deenes.model;

import lombok.*;

import java.util.LinkedList;

@Data
@Builder
@With
@Setter
@Getter
@ToString(of = {"trainStatsId", "lineNumber", "firstKilometer", "lastKilometer"})
@EqualsAndHashCode(of = {"trainStatsId", "firstKilometer", "lastKilometer", "lineNumber"})
public class TrainStats {

    private final LinkedList<Line> lineList;
    private Integer trainStatsId;
    private Double howManyKilometers;

    private Integer lineNumber;
    private Double firstKilometer;
    private Double lastKilometer;
    private Train trainEntity;
    private Analyse analyse;


}
