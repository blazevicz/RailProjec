package org.pl.deenes.services.dao;

import org.pl.deenes.model.Analyse;
import org.pl.deenes.model.Train;

public interface AnalyseDAO {

    Analyse save(Analyse analyse);


    Train find(Integer trainKwr);

}
