package org.pl.deenes.model;

import lombok.Builder;
import lombok.Value;
import org.pl.deenes.infrastructure.entity.LineEntity;

@Value
@Builder
public class Region {

    Integer id;
    Integer zlkRegionNumber;
    String actualWOS;
    String actualWOSlink;
    LineEntity line;
}
