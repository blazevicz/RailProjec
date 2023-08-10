package org.pl.deenes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "localization")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LocalizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "localization_id")
    Integer localizationId;
    @Column(name = "station")
    String station;
    @Column(name = "latitude")
    Double latitude;
    @Column(name = "longitude")
    Double longitude;
}
