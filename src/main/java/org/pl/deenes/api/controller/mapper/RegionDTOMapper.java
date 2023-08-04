package org.pl.deenes.api.controller.mapper;

import org.mapstruct.Mapper;
import org.pl.deenes.api.controller.dto.RegionDTO;
import org.pl.deenes.model.Region;

@Mapper(componentModel = "spring")
public interface RegionDTOMapper {

    RegionDTO mapToDTO(Region region);

    Region mapFromDTO(RegionDTO dto);
}
