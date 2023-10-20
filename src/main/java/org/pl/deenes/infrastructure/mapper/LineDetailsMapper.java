package org.pl.deenes.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.pl.deenes.infrastructure.entity.LineDetailsEntity;
import org.pl.deenes.model.LineDetails;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LineDetailsMapper {

    LineDetails mapFromEntity(LineDetailsEntity entity);

    LineDetailsEntity mapToEntity(LineDetails lineDetails);

}
