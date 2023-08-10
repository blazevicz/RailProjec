package org.pl.deenes.infrastructure.mapper;

import org.mapstruct.Condition;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.pl.deenes.infrastructure.entity.TrainStatsEntity;
import org.pl.deenes.model.TrainStats;

@Mapper(componentModel = "spring")
public interface TrainStatsMapper {

    TrainStats mapFromEntity(TrainStatsEntity entity,
                             @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @InheritInverseConfiguration
    TrainStatsEntity mapToEntity(TrainStats trainStats,
                                 @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Condition
    default boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }
    @DoIgnore
    default TrainStatsEntity mapToEntity(TrainStats analyse) {
        return mapToEntity(analyse, new CycleAvoidingMappingContext());
    }


}