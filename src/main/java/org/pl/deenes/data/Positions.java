package org.pl.deenes.data;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Positions {

    LEFT("left"),
    RIGHT("right"),
    TITLE("title"),
    ANALYSIS("analysis"),
    BRUTTOANALYSIS("bruttoAnalysis");

    final String value;


}
