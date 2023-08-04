package org.pl.deenes.model;

import lombok.*;

@Builder
@Setter
@ToString
@Getter
@EqualsAndHashCode(of = "actualWOS")
public class Region {
    private Integer regionId;
    private Integer zlkRegionNumber;
    private String actualWOS;
    private String actualWOSlink;
}
