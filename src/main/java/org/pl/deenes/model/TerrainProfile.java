package org.pl.deenes.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Builder
@Getter
@ToString
public class TerrainProfile {
    int lineNumber;
    double kilometer;
    double height;
    double slope;
}
