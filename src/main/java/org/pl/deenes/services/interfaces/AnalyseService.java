package org.pl.deenes.services.interfaces;

import org.pl.deenes.model.Analyse;
import org.pl.deenes.model.TrainStats;

import java.util.List;

public interface AnalyseService {
    Analyse creatingTrainAnalyse(TrainStats trainStats, List<String> stations);
}
