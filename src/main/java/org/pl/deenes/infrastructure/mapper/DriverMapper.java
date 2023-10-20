package org.pl.deenes.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.pl.deenes.infrastructure.entity.DriverEntity;
import org.pl.deenes.model.Driver;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DriverMapper {

    Driver mapFromEntity(DriverEntity entity);

    DriverEntity mapToEntity(Driver analyse);
}
