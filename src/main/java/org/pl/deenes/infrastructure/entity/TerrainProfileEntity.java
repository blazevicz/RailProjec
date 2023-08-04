package org.pl.deenes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "terrain_profile")
public class TerrainProfileEntity {

    @Column(name = "kilometer")
    double kilometer;
    @Column(name = "height")
    double height;
    @Column(name = "line_number")
    int lineNumber;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Integer profileId;

}
