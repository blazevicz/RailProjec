package org.pl.deenes.api.controller.mapper;

import org.mapstruct.Mapper;
import org.pl.deenes.api.controller.dto.AnalyseDTO;
import org.pl.deenes.model.Analyse;

@Mapper(componentModel = "spring")
public interface AnalyseDTOMapper {


    AnalyseDTO mapToDTO(Analyse analyse);

    Analyse mapFromDTO(AnalyseDTO dto);
}
