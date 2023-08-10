package org.pl.deenes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "trainKwr")
@Table(name = "train")
public class TrainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_id")
    private Integer trainId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "train_kwr")
    private Integer trainKwr;

    @Column(name = "date_plan")
    private LocalDate datePlan;

    @Column(name = "road_stats")
    private Double roadStats;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private DriverEntity driver;

    @Column(name = "train_number")
    private int trainNumber;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainEntity")
    private List<TrainStatsEntity> trainStats;
}
