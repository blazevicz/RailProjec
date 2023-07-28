package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.pl.deenes.model.Line;
import org.pl.deenes.services.interfaces.CalculateKilometersService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@Slf4j
@Service
public class CalculateKilometersServiceImpl implements CalculateKilometersService {

    private TimetableDetails timetableDetails;

    @Override
    public LinkedList<Line> createLinesAndAddToLineList(Double lastKilometer) {
        LinkedList<Line> lineList = new LinkedList<>();
        LinkedList<Double> allKilometersOnLine = new LinkedList<>();

        for (Number number : this.timetableDetails.getLineNumbers()) {
            int railLineNumber = 0;

            if (number instanceof Integer integer) {
                railLineNumber = integer;
                if (!allKilometersOnLine.isEmpty()) {
                    allKilometersOnLine = new LinkedList<>();
                }
            } else {
                allKilometersOnLine.add((Double) number);
                continue;
            }
            lineList.add(new Line(railLineNumber, allKilometersOnLine));
        }

        lineList.getLast().getKilometers().add(lastKilometer);
        return connectingDuplicatedLines(lineList);
    }

    @Contract("_ -> new")
    private @NonNull LinkedList<Line> connectingDuplicatedLines(@NonNull LinkedList<Line> lines) {

        return new LinkedList<>(lines.stream()
                .collect(Collectors.toMap(Line::getLineNumber,
                        line -> new Line(line.getLineNumber(), line.getKilometers()),
                        (line1, line2) -> {
                            List<Double> mergedKilometers = new ArrayList<>(line1.getKilometers());
                            mergedKilometers.addAll(line2.getKilometers());
                            return new Line(line1.getLineNumber(), mergedKilometers);
                        }))
                .values());

    }

}