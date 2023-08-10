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
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainDTO {

    Integer trainId;
    Integer trainKwr;
    String companyName;
    LocalDate datePlan;
    Double roadStats;
    Analyse analyse;
    Driver driver;
    Integer trainMaxWeight;
    Integer trainMaxLength;
    String startStation;
    String endStation;
    String trainType;
    LocomotiveType locomotiveType;
    Integer trainMaxSpeed;
    Integer brakePercent;
    List<TrainStats> trainStats;
    Integer trainNumber;

}
