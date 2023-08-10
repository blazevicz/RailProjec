package org.pl.deenes.services.interfaces;

import org.pl.deenes.model.Train;

public interface TrainService {
    Train trainCreate(String link);

    Train saveTrain(Train train);
}
