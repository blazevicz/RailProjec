package org.pl.deenes.api.controller.mapper;

import org.mapstruct.Mapper;
import org.pl.deenes.api.controller.dto.TerrainProfileDTO;
import org.pl.deenes.model.TerrainProfile;

@Mapper(componentModel = "spring")
public interface TerrainProfileDTOMapper {
    TerrainProfile mapFromDTO(TerrainProfileDTO dto);
    TerrainProfileDTO mapToDTO(TerrainProfile model);
}
