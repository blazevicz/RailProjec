package org.pl.deenes.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LineDetails {
    Integer id;
    String startStation;
    String endStation;
    Integer lineNumber;
    Double startKilometer;
    Double endKilometer;
    Integer page;
    Integer railwayRegion;
    String wosUpdateVersion;
    Set<TerrainProfile> profile;

}
