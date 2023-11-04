package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.infrastructure.repositories.dao.TrainDAO;
import org.pl.deenes.model.Train;
import org.pl.deenes.services.interfaces.ResultService;
import org.pl.deenes.services.interfaces.TrainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@Service
@AllArgsConstructor
public class ResultServiceImpl implements ResultService {

    private TrainService trainServiceImpl;
    private TrainDAO trainDAO;
    @Override
    @Transactional
    public void runningMethod(String link) {
        Train train = null;
        try {
            train = trainServiceImpl.trainCreate(link);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        trainDAO.save(train);
    }
}
