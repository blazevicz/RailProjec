package org.pl.deenes.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pl.deenes.configuration.SpringConfiguration;
import org.pl.deenes.data.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig
@ContextConfiguration(classes = SpringConfiguration.class)
class ResultServiceImplITTest {

    @Autowired
    private TrainService trainServiceImpl;

    @BeforeEach
    void set() {
        Assertions.assertNotNull(trainServiceImpl);
    }

    @Test
    void runningMethod() {
        Train expected = Train.builder()
                .companyName("a")
                .build();

        //when
        Train result = trainServiceImpl.trainCreate();

        Assertions.assertEquals(expected, result);
    }
}