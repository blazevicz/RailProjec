package org.pl.deenes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "region")
@EqualsAndHashCode
public class RegionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Integer regionId;
    @Column(name = "region")
    private Integer zlkRegionNumber;
    @Column(name = "wos")
    private String actualWOS;
    @Column(name = "wos_link")
    private String actualWOSlink;

    @ManyToOne
    @JoinColumn(name = "line_id")
    private LineEntity line;
}
