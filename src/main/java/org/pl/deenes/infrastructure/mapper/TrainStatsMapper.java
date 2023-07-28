package org.pl.deenes.infrastructure.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.pl.deenes.infrastructure.entity.TrainStatsEntity;
import org.pl.deenes.model.TrainStats;

@Mapper(componentModel = "spring")
public interface TrainStatsMapper {

    TrainStats mapFromEntity(TrainStatsEntity entity,
                             @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);


    TrainStatsEntity mapToEntity(TrainStats trainStats,
                                 @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);


    @DoIgnore
    default TrainStatsEntity mapToEntity(TrainStats analyse) {
        return mapToEntity(analyse, new CycleAvoidingMappingContext());
    }


}