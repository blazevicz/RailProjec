package org.pl.deenes.api.controller.mapper;

import org.mapstruct.Mapper;
import org.pl.deenes.api.controller.dto.DriverDTO;
import org.pl.deenes.model.Driver;

@Mapper(componentModel = "spring")
public interface DriverDTOMapper {

    DriverDTO mapToDTO(Driver driver);

    Driver mapFromDTO(DriverDTO driverDTO);

}
