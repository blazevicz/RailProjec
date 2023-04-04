package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.data.Train;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ResultServiceImpl implements ResultService {

    private TrainService trainServiceImpl;

    @Override
    public void runningMethod() {
        Train train = trainServiceImpl.trainCreate();
        log.error(train.toString());


    }
}
