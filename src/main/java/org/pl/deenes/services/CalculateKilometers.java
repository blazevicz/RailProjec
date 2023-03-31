package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.data.Line;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@AllArgsConstructor
@Data
@Slf4j
@Service
public class CalculateKilometers {

    private Kilometers kilometers;

    LinkedList<Line> createLinesAndAddToLineList(Double lastKilometer) {
        LinkedList<Line> lineList = new LinkedList<>();
        LinkedList<Double> allKilometersOnLine = new LinkedList<>();

        for (Number number : this.kilometers.getResult()) {
            int a = 0;
            if (number instanceof Integer integer) {
                a = integer;

                if (!allKilometersOnLine.isEmpty()) {
                    allKilometersOnLine = new LinkedList<>();
                }
            } else {
                allKilometersOnLine.add((Double) number);
                continue;
            }
            lineList.add(new Line(a, allKilometersOnLine));
        }
        lineList.getLast().getKilometers().add(lastKilometer);
        return lineList;
    }


}