package org.pl.deenes.api.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.pl.deenes.api.controller.dto.AnalyseDTO;
import org.pl.deenes.model.Analyse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnalyseDTOMapper {
    AnalyseDTO mapToDTO(Analyse analyse);
    Analyse mapFromDTO(AnalyseDTO dto);
}
