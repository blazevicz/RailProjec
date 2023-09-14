package org.pl.deenes.api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pl.deenes.model.LocomotiveType;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalyseDTO {
    private Integer trainKwr;
    private Integer trainMaxWeight;
    private Integer trainMaxLength;
    private String startStation;
    private String endStation;
    private String trainType;
    private LocomotiveType locomotiveType;
    private Integer trainMaxSpeed;
    private Integer brakePercent;
    private Integer trainNumber;
}
