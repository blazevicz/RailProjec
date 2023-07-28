package org.pl.deenes.model;

import lombok.*;
import org.pl.deenes.infrastructure.entity.TrainStatsEntity;

import java.time.LocalDate;
import java.util.List;

@With
@Value
@Builder
@ToString(exclude = {"driver"})
@EqualsAndHashCode(of = "trainId")

public class Train {

    Integer trainId;
    Integer trainKwr;
    String companyName;
    LocalDate datePlan;
    Double roadStats;
    Analyse analyse;
    // TrainStats trainStats;
    Driver driver;
    List<TrainStatsEntity> trainStats;
    //  Set<Line> line;
}


