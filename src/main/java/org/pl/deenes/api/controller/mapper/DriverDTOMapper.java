package org.pl.deenes.api.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.pl.deenes.api.controller.dto.DriverDTO;
import org.pl.deenes.model.Driver;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DriverDTOMapper {
    DriverDTO mapToDTO(Driver driver);
    Driver mapFromDTO(DriverDTO driverDTO);

}
