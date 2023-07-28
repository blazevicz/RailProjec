package org.pl.deenes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "line")
public class LineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_id")
    private Integer lineId;
    @Column(name = "line_number", unique = true)
    private Integer lineNumber;
    @Column(name = "relation_from")
    private String relationFrom;
    @Column(name = "relation_to")
    private String relationTo;
    @OneToOne
    @JoinColumn(name = "line_details_id")
    private LineDetailsEntity lineEntry;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "line", cascade = CascadeType.ALL)
    private Set<RegionEntity> zlk;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "line", cascade = CascadeType.ALL)
    private Set<TerrainProfileEntity> profile;
 /*   @OneToOne(mappedBy = "line", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private TrainStatsEntity trainStats;*/
}
