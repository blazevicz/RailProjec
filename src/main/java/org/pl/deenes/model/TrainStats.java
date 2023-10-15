package org.pl.deenes.model;

import lombok.*;

import java.util.LinkedList;

@Data
@Builder
@With
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(of = {"trainStatsId", "lineNumber", "firstKilometer", "lastKilometer", "station"})
@EqualsAndHashCode(of = {"trainStatsId", "firstKilometer", "lastKilometer", "lineNumber"})
public class TrainStats {
    private LinkedList<Line> lineList;
    private Integer trainStatsId;
    private Double howManyKilometers;
    private String station;
    private Integer lineNumber;
    private Double firstKilometer;
    private Double lastKilometer;
    private Train trainEntity;
    private Analyse analyse;
}