package org.pl.deenes.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Data
@RequiredArgsConstructor
@Service
public class TrainStats {
    private final LinkedList<Line> lineList;
    private Double howManyKilometers;

}
