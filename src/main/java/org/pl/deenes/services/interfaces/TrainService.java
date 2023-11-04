package org.pl.deenes.services.interfaces;

import org.pl.deenes.model.Train;

import java.io.IOException;

public interface TrainService {
    Train trainCreate(String link) throws IOException;

    Train saveTrain(Train train);
}
