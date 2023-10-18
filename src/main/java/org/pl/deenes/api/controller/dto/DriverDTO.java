package org.pl.deenes.api.controller.dto;

import lombok.*;
import org.hibernate.validator.constraints.pl.PESEL;
import org.pl.deenes.infrastructure.entity.RoleEntity;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "driverId")
public class DriverDTO {

    private String name;
    private String surname;
    @PESEL
    private String pesel;
    private Boolean active;
    private Integer driverId;
    private Set<RoleEntity> roles;
}
