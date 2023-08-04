package org.pl.deenes.api.controller.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TerrainProfileDTO {
    int profileId;
    int lineNumber;
    double kilometer;
    double height;
    double slope;
}
