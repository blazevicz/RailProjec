package org.pl.deenes.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.pl.deenes.configuration.SpringConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig(classes = SpringConfiguration.class)
class CalculateKilometersServiceImplSpringTest {

    @Autowired
    private KilometersServiceImpl kilometersServiceImpl;

    @BeforeEach
    public void setUp() {
        Assertions.assertNotNull(kilometersServiceImpl);
    }

    @Test
    void createLinesAndAddToLineList() {
        Mockito.when(kilometersServiceImpl.getLineNumbers()).thenReturn(List.of(11, 1.1, 2.2, 1, 333, 555));

        createLinesAndAddToLineList();

    }

    @Test
    void getKilometersServiceImpl() {
    }

    @Test
    void setKilometersServiceImpl() {
    }
}