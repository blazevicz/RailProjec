package org.pl.deenes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "train", cascade = CascadeType.ALL)
    private Set<LineEntity> line;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "train_analyse_id")
    private AnalyseEntity analyse;
}
