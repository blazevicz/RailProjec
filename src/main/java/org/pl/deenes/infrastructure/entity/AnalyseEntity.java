package org.pl.deenes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "train_analyse")
public class AnalyseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_analyse_id")
    private Integer trainAnalyseId;

    @Column(name = "train_number")
    private int trainNumber;

    @Column(name = "train_kwr")
    private int trainKwr;

    @Column(name = "train_max_weight")
    private int trainMaxWeight;

    @Column(name = "train_max_length")
    private int trainMaxLength;

    @Column(name = "start_station")
    private String startStation;

    @Column(name = "end_station")
    private String endStation;

    @Column(name = "train_type")
    private String trainType;

    @Column(name = "locomotive_type")
    private String locomotiveType;

    @Column(name = "train_max_speed")
    private int trainMaxSpeed;

    @Column(name = "brake_percent")
    private int brakePercent;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "train_stats_id")
    private TrainStatsEntity trainStatsEntity;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "analyse")
    @JoinColumn(name = "train_id")
    private TrainEntity trainEntity;

}
