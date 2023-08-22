package org.pl.deenes.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.pl.deenes.infrastructure.entity.LineEntity;
import org.pl.deenes.infrastructure.entity.RoleEntity;

import java.util.Set;

@Data
@Builder
@EqualsAndHashCode
public class Driver {

    private Integer driverId;
    private String name;
    private String surname;
    private String pesel;

    private Set<LocomotiveType> locomotiveAuthorization;
    private Set<LineEntity> lineAuthorization;
    private Set<Train> trains;

    private Boolean active;
    private Set<RoleEntity> roles;
    private String password;
}
