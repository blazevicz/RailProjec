package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.infrastructure.repositories.dao.TrainDAO;
import org.pl.deenes.model.Train;
import org.pl.deenes.services.interfaces.ResultService;
import org.pl.deenes.services.interfaces.TrainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class ResultServiceImpl implements ResultService {

    private TrainService trainServiceImpl;
    private TrainDAO trainDAO;
    private TrainService trainService;

    @Override
    @Transactional
    public void runningMethod(String link) {
        Train train = trainServiceImpl.trainCreate(link);
        trainDAO.save(train);


    }

    @Transactional
    public Train saveTrain(Train train) {
        return trainDAO.save(train);

    }
}
