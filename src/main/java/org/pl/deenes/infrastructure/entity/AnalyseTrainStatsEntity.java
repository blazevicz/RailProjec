package org.pl.deenes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "analyse_train_stats")
@AllArgsConstructor
@NoArgsConstructor
public class AnalyseTrainStatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "analyse_train_stats_id")
    private Integer analyseTrainStatsId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "train_analyse_id")
    private AnalyseEntity analyse;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "train_stats_id")
    private TrainStatsEntity trainStats;
}
