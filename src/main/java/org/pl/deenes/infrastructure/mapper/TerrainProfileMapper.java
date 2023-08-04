package org.pl.deenes.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.pl.deenes.infrastructure.entity.TerrainProfileEntity;
import org.pl.deenes.model.TerrainProfile;

@Mapper(componentModel = "spring")
public interface TerrainProfileMapper {

    TerrainProfile mapFromEntity(TerrainProfileEntity entity);

    TerrainProfileEntity mapToEntity(TerrainProfile model);
}
