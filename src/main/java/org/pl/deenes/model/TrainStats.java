package org.pl.deenes.model;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.With;

import java.util.List;
import java.util.Map;

@Data
@Builder
@With
@Setter
public class TrainStats {
    //bylo linked list
    private final List<Line> lineList;
    private Double howManyKilometers;
    private Analyse analyse;
    private Double firstKilometer;
    private Double lastKilometer;
    private Map<Integer, List<Double>> lineWithFirstLastKm;

}
