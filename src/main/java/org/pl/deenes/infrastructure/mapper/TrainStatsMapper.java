package org.pl.deenes.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pl.deenes.infrastructure.entity.TrainStatsEntity;
import org.pl.deenes.model.TrainStats;

@Mapper(componentModel = "spring")
public interface TrainStatsMapper {

    TrainStats mapFromEntity(TrainStatsEntity entity);

    @Mapping(target = "trainEntity", ignore = true)
    TrainStatsEntity mapToEntity(TrainStats trainStats);


}