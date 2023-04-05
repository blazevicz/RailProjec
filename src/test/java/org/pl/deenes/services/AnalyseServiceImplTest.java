package org.pl.deenes.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pl.deenes.data.Analyse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AnalyseServiceImplTest {

    @InjectMocks
    private AnalyseServiceImpl analyseServiceImpl;
    @Mock
    private ReadKilometersServiceImpl readKilometersServiceImpl;

    @Test
    void someTest() {
        String textForRegion = "TME 464028/9 (666401) Relacja Dąbrowa Górnicza Towarowa - Chorula Cementownia 2";

        String grossTextToAnalyse = """
                Lok I Obc.lok Vmax
                Lok II %
                Lok III Dł.Poc. Rodz.h
                ET22 1979 80
                48
                419 P
                ET22 80
                1979
                48
                419
                P
                ET22 1979 80
                48
                419 P
                ET22 1979 80
                48
                419 P
                ET22 80
                1979
                48
                419 P
                ET22 80
                1979
                48
                419
                P
                ET22 80
                1979
                48
                419
                P""";

        Mockito.when(readKilometersServiceImpl.getTextToAnalyse()).thenReturn(textForRegion);
        Mockito.when(readKilometersServiceImpl.getBruttoTextToAnalyse()).thenReturn(grossTextToAnalyse);

        Analyse expectedAnalyse = Analyse.builder()
                .locomotiveType("ET22")
                .trainMaxWeight(1979)
                .trainMaxSpeed(80)
                .brakePercent(48)
                .trainMaxLength(419)
                .trainType("TME")
                .trainNumber(464028)
                .trainKwr(666401)
                .startStation("Dąbrowa Górnicza Towarowa")
                .endStation("Chorula Cementownia")
                .build();

        Analyse actualAnalyse = analyseServiceImpl.creatingTrainAnalyse();

        assertEquals(expectedAnalyse, actualAnalyse);
    }
}