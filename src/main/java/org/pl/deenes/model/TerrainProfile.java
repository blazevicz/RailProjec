package org.pl.deenes.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TerrainProfile {
    double kilometer;
    double height;
    double slope;
}
