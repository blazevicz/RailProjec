package org.pl.deenes.services;

import org.pl.deenes.model.Analyse;
import org.pl.deenes.model.TrainStats;

public interface AnalyseService {
    Analyse creatingTrainAnalyse(TrainStats trainStats);
}
