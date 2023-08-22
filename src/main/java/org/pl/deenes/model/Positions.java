package org.pl.deenes.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Positions {

    LEFT("left"),
    RIGHT("right"),
    TITLE("title"),
    ANALYSIS("analysis"),
    BRUTTO_ANALYSIS("bruttoAnalysis"),
    COMPANY_NAME("companyName"),
    LEFT_STATIONS("leftStations"),
    RIGHT_STATIONS("rightStations");

    final String value;


}
