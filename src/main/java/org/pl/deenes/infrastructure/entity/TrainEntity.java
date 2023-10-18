package org.pl.deenes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "trainKwr")
@Table(name = "train")
public class TrainEntity {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainEntity", fetch = FetchType.EAGER)
    Set<TrainStatsEntity> trainStats;
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
    private Integer trainNumber;
    @Column(name = "train_max_weight")
    private Integer trainMaxWeight;
    @Column(name = "train_max_length")
    private Integer trainMaxLength;
    @Column(name = "start_station")
    private String startStation;
    @Column(name = "end_station")
    private String endStation;
    @Column(name = "train_type")
    private String trainType;
    @Column(name = "locomotive_type")
    private String locomotiveType;
    @Column(name = "train_max_speed")
    private Integer trainMaxSpeed;
    @Column(name = "brake_percent")
    private Integer brakePercent;
}
