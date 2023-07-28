package org.pl.deenes.infrastructure.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "line_details")
public class LineDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_details_id")
    private Integer lineDetailsId;
    @Column(name = "start_station")
    private String startStation;
    @Column(name = "end_station")
    private String endStation;
    @Column(name = "line_number")
    private Integer lineNumber;
    @Column(name = "start_kilometer")
    private Double startKilometer;
    @Column(name = "end_kilometer")
    private Double endKilometer;
    @Column(name = "page")
    private Integer page;
    @Column(name = "railway_region")
    private Integer railwayRegion;
/*    @Column(name = "wos_update_ver")
    private String wosUpdateVersion;*/

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "line")
    private Set<TerrainProfileEntity> profile;*/

    //link do listy linii


}
