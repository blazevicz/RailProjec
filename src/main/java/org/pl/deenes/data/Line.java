package org.pl.deenes.data;

import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Line {
    private final Integer lineNumber;
    private final List<Double> kilometers;
    private Double size;

}
