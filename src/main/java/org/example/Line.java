package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
public class Line {
    private Integer lineNumber;
    private LinkedList<Double> kilometers;
    private Double size;

    public Line(Integer lineNumber, LinkedList<Double> kilometers) {
        this.lineNumber = lineNumber;
        this.kilometers = kilometers;
    }

    @Override
    public String toString() {
        return "Line{" +
                "lineNumber=" + lineNumber +
                ", kilometers=" + kilometers +
                ", size=" + size +
                '}';
    }
}
