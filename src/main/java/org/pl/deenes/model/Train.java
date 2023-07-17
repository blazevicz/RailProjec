package org.pl.deenes.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@With
@Value
@Builder
@ToString(exclude = {"driver", "line"})
@EqualsAndHashCode(of = "trainId")

public class Train {

    Integer trainId;
    Integer trainKwr;
    String companyName;
    LocalDate datePlan;
    Double roadStats;
    Analyse analyse;
    Set<Line> line;
    Driver driver;

}


