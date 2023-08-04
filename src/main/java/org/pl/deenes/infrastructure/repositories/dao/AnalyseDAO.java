package org.pl.deenes.infrastructure.repositories.dao;

import org.pl.deenes.model.Analyse;
import org.pl.deenes.model.Train;

import java.util.List;

public interface AnalyseDAO {

    Analyse save(Analyse analyse);

    Train find(Integer trainKwr);

    List<Analyse> findAllByTrainKwr(Integer trainKwr);


}
