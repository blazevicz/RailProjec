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
    @Column(name = "line_number")
    private Integer lineNumber;
    @Column(name = "size")
    private Double size;

    @OneToOne
    @JoinColumn(name = "line_details_id")
    private LineDetailsEntity lineEntry;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "line")
    private Set<RegionEntity> zlk;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private TrainStatsEntity roadStats;

}
