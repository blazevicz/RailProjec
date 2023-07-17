package org.pl.deenes.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.pl.deenes.infrastructure.entity.LineEntity;
import org.pl.deenes.model.Line;

@Mapper(componentModel = "spring")
public interface LineMapper {

    Line mapFromEntity(LineEntity entity);

    LineEntity mapToEntity(Line lineDetails);

}
