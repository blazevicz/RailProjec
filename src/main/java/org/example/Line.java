package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Line {
    private Integer lineNumber;
    private List<Double> kilometers;
    private Double size;

    public Line(Integer lineNumber, List<Double> kilometers) {
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
