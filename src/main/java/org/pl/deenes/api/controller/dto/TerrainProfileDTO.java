package org.pl.deenes.api.controller.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TerrainProfileDTO {
    private int profileId;
    private int lineNumber;
    private double kilometer;
    private double height;
    private double slope;
}
