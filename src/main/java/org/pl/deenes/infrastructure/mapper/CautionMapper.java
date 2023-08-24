package org.pl.deenes.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.pl.deenes.infrastructure.entity.CautionEntity;
import org.pl.deenes.model.Caution;

@Mapper(componentModel = "spring")
public interface CautionMapper {

    Caution mapFromEntity(CautionEntity entity);
    CautionEntity mapToEntity(Caution caution);
}
