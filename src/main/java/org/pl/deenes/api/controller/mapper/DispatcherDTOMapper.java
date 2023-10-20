package org.pl.deenes.api.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.pl.deenes.api.controller.dto.DispatcherDTO;
import org.pl.deenes.model.Dispatcher;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DispatcherDTOMapper {

    DispatcherDTO mapToDTO(Dispatcher dispatcher);

    Dispatcher mapFromDTO(DispatcherDTO dispatcher);
}
