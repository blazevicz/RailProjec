package org.pl.deenes.data;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class Train {

    private String companyName;
    private LocalDate datePlan;
    private RoadStats roadStats;
    private List<Line> lineList;
    private Analyse analyse;
}
