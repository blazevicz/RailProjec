package org.pl.deenes.api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pl.deenes.model.Analyse;
import org.pl.deenes.model.Driver;
import org.pl.deenes.model.Line;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainDTO {

    Integer trainKwr;
    String companyName;
    LocalDate datePlan;
    Double roadStats;
    Analyse analyse;
    Set<Line> line;
    Driver driver;

}
