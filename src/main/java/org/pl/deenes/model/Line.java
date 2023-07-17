package org.pl.deenes.model;

import lombok.*;
import org.pl.deenes.infrastructure.entity.LineDetailsEntity;
import org.pl.deenes.infrastructure.entity.RegionEntity;
import org.pl.deenes.infrastructure.entity.TrainEntity;
import org.pl.deenes.infrastructure.entity.TrainStatsEntity;

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
    private Double size;
    private LineDetailsEntity lineEntry;
    private Set<RegionEntity> zlk;
    private TrainEntity train;
    private TrainStatsEntity trainStats;

    private String relationFrom;
    private String relationTo;


}
