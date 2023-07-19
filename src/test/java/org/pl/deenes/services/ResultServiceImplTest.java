package org.pl.deenes.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pl.deenes.model.Train;
import org.pl.deenes.services.interfaces.TrainService;
import org.slf4j.Logger;

import static org.mockito.Mockito.when;

class ResultServiceImplTest {
    @Mock
    TrainService trainServiceImpl;
    @Mock
    Logger log;
    @InjectMocks
    ResultServiceImpl resultServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRunningMethod() {
        when(trainServiceImpl.trainCreate()).thenReturn(Train.builder().build());

        resultServiceImpl.runningMethod();
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme