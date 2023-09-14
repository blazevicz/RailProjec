package org.pl.deenes.api.controller.dto;

import lombok.*;
import org.pl.deenes.model.Line;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RegionDTO {

    private Integer id;
    private Integer zlkRegionNumber;
    private String actualWOS;
    private String actualWOSlink;
    private Line line;
}
