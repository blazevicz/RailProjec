package org.pl.deenes.model;

import lombok.*;
import org.pl.deenes.infrastructure.entity.TerrainProfileEntity;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Line {
    private final Integer lineNumber;
    private final List<Double> kilometers;
    private Integer lineId;
    private Double size;
    private LineDetails lineEntry;
    private Set<Region> zlk;
    private String relationFrom;
    private String relationTo;
    private Set<TerrainProfileEntity> profile;


}
