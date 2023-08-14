package org.pl.deenes.infrastructure.mapper;

import org.pl.deenes.infrastructure.entity.TrainEntity;
import org.pl.deenes.model.Train;

//@Mapper(componentModel = "spring")
public interface TrainMapper {


    TrainEntity mapToEntity(Train lineDetails);

    Train mapFromEntity(TrainEntity entity);


}


