package org.pl.deenes.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.pl.deenes.infrastructure.entity.LineEntity;

import java.util.Set;

@Data
@Builder
@EqualsAndHashCode
public class Driver {

    private Integer driverId;
    private String name;
    private String surname;
    private Integer pesel;

    private Set<LocomotiveType> locomotiveAuthorization;
    private Set<LineEntity> lineAuthorization;
    private Set<Train> trains;


}
