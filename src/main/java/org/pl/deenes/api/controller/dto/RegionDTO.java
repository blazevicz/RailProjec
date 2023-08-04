package org.pl.deenes.api.controller.dto;

import lombok.*;
import org.pl.deenes.model.Line;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RegionDTO {

    Integer id;
    Integer zlkRegionNumber;
    String actualWOS;
    String actualWOSlink;
    Line line;
}
