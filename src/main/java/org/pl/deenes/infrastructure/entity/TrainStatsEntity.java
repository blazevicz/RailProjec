package org.pl.deenes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "first_kilometer")
    private Double firstKilometer;
    @Column(name = "last_kilometer")
    private Double lastKilometer;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "line_id")
    private LineEntity line;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "train_analyse_id")
    private AnalyseEntity analyse;

}
