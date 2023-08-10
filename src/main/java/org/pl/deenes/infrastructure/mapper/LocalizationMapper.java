package org.pl.deenes.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.pl.deenes.infrastructure.entity.LocalizationEntity;
import org.pl.deenes.model.Localization;

@Mapper(componentModel = "spring")
public interface LocalizationMapper {

    Localization mapFromEntity(LocalizationEntity entity);

    LocalizationEntity mapToEntity(Localization model);
}
