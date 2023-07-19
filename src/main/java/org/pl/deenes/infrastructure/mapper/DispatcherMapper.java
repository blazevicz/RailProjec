package org.pl.deenes.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.pl.deenes.infrastructure.entity.DispatcherEntity;
import org.pl.deenes.model.Dispatcher;

@Mapper(componentModel = "spring")
public interface DispatcherMapper {

    Dispatcher mapFromEntity(DispatcherEntity entity);

    DispatcherEntity mapToEntity(Dispatcher dispatcher);
}
