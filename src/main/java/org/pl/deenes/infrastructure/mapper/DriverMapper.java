package org.pl.deenes.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.pl.deenes.infrastructure.entity.DriverEntity;
import org.pl.deenes.model.Driver;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    Driver mapFromEntity(DriverEntity entity);

    DriverEntity mapToEntity(Driver analyse);
}
