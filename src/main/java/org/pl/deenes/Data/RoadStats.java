package org.pl.deenes.Data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
@Data
@AllArgsConstructor
public class RoadStats {
    private LinkedList<Line> lineList;
    private Double howManyKilometers;
}
