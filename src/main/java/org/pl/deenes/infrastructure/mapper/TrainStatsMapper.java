package org.pl.deenes.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.pl.deenes.infrastructure.entity.TrainStatsEntity;
import org.pl.deenes.model.TrainStats;

@Mapper(componentModel = "spring")
public interface TrainStatsMapper {

    TrainStats mapFromEntity(TrainStatsEntity entity);

    TrainStatsEntity mapToEntity(TrainStats trainStats);
}
