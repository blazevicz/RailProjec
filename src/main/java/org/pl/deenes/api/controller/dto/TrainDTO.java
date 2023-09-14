package org.pl.deenes.api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pl.deenes.model.Analyse;
import org.pl.deenes.model.Driver;
import org.pl.deenes.model.LocomotiveType;
import org.pl.deenes.model.TrainStats;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainDTO {
    private LocalDate datePlan;
    private Double roadStats;
    private Analyse analyse;
    private Driver driver;
    private LocomotiveType locomotiveType;
    private Set<TrainStats> trainStats;
    private Integer trainId;
    private Integer trainKwr;
    private String companyName;
    private Integer trainMaxWeight;
    private Integer trainMaxLength;
    private String startStation;
    private String endStation;
    private String trainType;
    private Integer trainMaxSpeed;
    private Integer brakePercent;
    private Integer trainNumber;
}
