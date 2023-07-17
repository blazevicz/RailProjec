package org.pl.deenes.api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pl.deenes.model.Line;
import org.pl.deenes.model.LocomotiveType;
import org.pl.deenes.model.Train;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {

    Integer id;
    String name;
    String surname;
    Set<LocomotiveType> locomotiveAuthorization;
    Set<Line> lineAuthorization;
    Set<Train> trains;
}
