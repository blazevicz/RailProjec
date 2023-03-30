package org.pl.deenes.Data;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Data
@RequiredArgsConstructor
@Service
public class RoadStats {
    private final LinkedList<Line> lineList;
    private Double howManyKilometers;
}
