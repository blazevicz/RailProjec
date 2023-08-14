package org.pl.deenes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "train_stats")
@EqualsAndHashCode(of = {"firstKilometer", "lastKilometer", "lineNumber"})
@ToString
public class TrainStatsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_stats_id")
    Integer trainStatsId;

    @Column(name = "first_kilometer")
    Double firstKilometer;

    @Column(name = "last_kilometer")
    Double lastKilometer;

    @Column(name = "line_number")
    Integer lineNumber;

    @ManyToOne
    @JoinColumn(name = "train_id")
    TrainEntity trainEntity;
}
