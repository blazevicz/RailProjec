package org.pl.deenes.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
@Builder
public class Train {

    String companyName;
    LocalDate datePlan;
    Double roadStats;
    Analyse analyse;
    List<Line> lineList;
    List<Driver> driver;

}
