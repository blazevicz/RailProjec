package org.pl.deenes.api.controller.mapper;

import org.mapstruct.Mapper;
import org.pl.deenes.api.controller.dto.TrainDTO;
import org.pl.deenes.model.Train;

@Mapper(componentModel = "spring")
public interface TrainDTOMapper {

    TrainDTO mapToDTO(Train train);

    Train mapFromDTO(TrainDTO trainDTO);

}
