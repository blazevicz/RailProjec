package org.pl.deenes.data;


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
@Table(name = "line_entry")
public class LineEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
    private Integer page;
    @Column(name = "railway_region")
    private Integer railwayRegion;
    @Column(name = "wos_update_ver")
    private String wosUpdateVersion;

}
