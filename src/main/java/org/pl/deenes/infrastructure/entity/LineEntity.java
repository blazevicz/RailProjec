package org.pl.deenes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
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

    @Column(name = "relation_from")
    private String relationFrom;
    @Column(name = "relation_to")
    private String relationTo;

    @ElementCollection
    @CollectionTable(name = "line_kilometers", joinColumns = @JoinColumn(name = "line_id"))
    @Column(name = "kilometers")
    private List<Double> kilometers;

    @OneToOne
    @JoinColumn(name = "line_details_id")
    private LineDetailsEntity lineEntry;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "line")
    private TrainStatsEntity trainStats;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "line")
    private Set<RegionEntity> zlk;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private TrainEntity train;
/*
    @ManyToOne
    @JoinColumn(name = "train_stats_id")
    private TrainStatsEntity trainStats;*/

}
