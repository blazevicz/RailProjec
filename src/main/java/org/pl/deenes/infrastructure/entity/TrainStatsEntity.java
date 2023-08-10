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
@EqualsAndHashCode
public class TrainStatsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_stats_id")
    private Integer trainStatsId;

    @Column(name = "first_kilometer")
    private Double firstKilometer;

    @Column(name = "last_kilometer")
    private Double lastKilometer;

    @Column(name = "line_number")
    private Integer lineNumber;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private TrainEntity trainEntity;
}
