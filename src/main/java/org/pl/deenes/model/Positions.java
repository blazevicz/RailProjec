package org.pl.deenes.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Positions {

    LEFT("left"),
    RIGHT("right"),
    TITLE("title"),
    ANALYSIS("analysis"),
    BRUTTOANALYSIS("bruttoAnalysis"),
    COMPANYNAME("companyName");

    final String value;


}
