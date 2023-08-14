package org.pl.deenes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "trainKwr")
@Table(name = "train")
public class TrainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_id")
    Integer trainId;

    @Column(name = "company_name")
    String companyName;

    @Column(name = "train_kwr")
    Integer trainKwr;

    @Column(name = "date_plan")
    LocalDate datePlan;

    @Column(name = "road_stats")
    Double roadStats;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    DriverEntity driver;

    @Column(name = "train_number")
    Integer trainNumber;

    @Column(name = "train_max_weight")
    Integer trainMaxWeight;

    @Column(name = "train_max_length")
    Integer trainMaxLength;

    @Column(name = "start_station")
    String startStation;

    @Column(name = "end_station")
    String endStation;

    @Column(name = "train_type")
    String trainType;

    @Column(name = "locomotive_type")
    String locomotiveType;

    @Column(name = "train_max_speed")
    Integer trainMaxSpeed;

    @Column(name = "brake_percent")
    Integer brakePercent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainEntity", fetch = FetchType.EAGER)
    Set<TrainStatsEntity> trainStats;
}
