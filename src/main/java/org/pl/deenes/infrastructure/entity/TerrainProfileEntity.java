package org.pl.deenes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "terrain_profile")
public class TerrainProfileEntity {
    @Column(name = "kilometer")
    double kilometer;
    @Column(name = "height")
    double height;
    @Column(name = "slope")
    double slope;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "terrain_profile_id")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "line_id")
    private LineEntity line;
}
