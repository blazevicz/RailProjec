package org.pl.deenes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedList;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "train_stats")
public class TrainStatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_stats_id")
    private Integer trainStatsId;

    @Column(name = "distance")
    private Double howManyKilometers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roadStats")
    private LinkedList<LineEntity> lineList;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_analyse_id")
    private AnalyseEntity analyse;
}
