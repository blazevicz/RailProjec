package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
@ToString
@Getter
@Setter
@AllArgsConstructor
public class RoadStats {
    private LinkedList<Line> lineList;
    private Double howManyKilometers;
}
