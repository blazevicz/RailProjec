package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.data.Train;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class Result {

    private TrainService trainService;

    public void runningMethod() {
        Train train = trainService.trainCreate();
        log.info(train.toString());


    }
}
