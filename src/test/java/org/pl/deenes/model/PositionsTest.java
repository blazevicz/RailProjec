package org.pl.deenes.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PositionsTest {
    @Test
    void enumShouldGiveBackCorrectName() {

        Assertions.assertEquals("left", Positions.LEFT.value);
        Assertions.assertEquals("right", Positions.RIGHT.value);
        Assertions.assertEquals("title", Positions.TITLE.value);
        Assertions.assertEquals("analysis", Positions.ANALYSIS.value);
        Assertions.assertEquals("bruttoAnalysis", Positions.BRUTTO_ANALYSIS.value);
        Assertions.assertEquals("companyName", Positions.COMPANY_NAME.value);
        Assertions.assertEquals("leftStations", Positions.LEFT_STATIONS.value);
        Assertions.assertEquals("rightStations", Positions.RIGHT_STATIONS.value);
    }
}