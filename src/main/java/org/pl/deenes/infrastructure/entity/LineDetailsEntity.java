package org.pl.deenes.infrastructure.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
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

}
