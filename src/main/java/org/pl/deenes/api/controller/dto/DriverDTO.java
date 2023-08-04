package org.pl.deenes.api.controller.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DriverDTO {

    Integer driverId;
    String name;
    String surname;
    Integer pesel;

}
