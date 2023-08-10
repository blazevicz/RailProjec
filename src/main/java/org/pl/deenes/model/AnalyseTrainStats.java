package org.pl.deenes.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class AnalyseTrainStats {

    private Integer analyseTrainStatsId;
    private Analyse analyse;
    private TrainStats trainStats;
}
