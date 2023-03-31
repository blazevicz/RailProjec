package org.pl.deenes.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Line {
    private final Integer lineNumber;
    private final List<Double> kilometers;
    private Double size;

}
