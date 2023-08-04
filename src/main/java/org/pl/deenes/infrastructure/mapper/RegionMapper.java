package org.pl.deenes.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.pl.deenes.infrastructure.entity.RegionEntity;
import org.pl.deenes.model.Region;

@Mapper(componentModel = "spring")
public interface RegionMapper {

    Region mapFromEntity(RegionEntity entity);

    RegionEntity mapToEntity(Region model);
}
