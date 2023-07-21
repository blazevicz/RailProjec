package org.pl.deenes.api.controller.mapper;

import org.pl.deenes.api.controller.dto.AnalyseDTO;
import org.pl.deenes.model.Analyse;

public interface AnalyseDTOMapper {


    AnalyseDTO mapToDTO(Analyse analyse);

    Analyse mapFromDTO(AnalyseDTO dto);
}
