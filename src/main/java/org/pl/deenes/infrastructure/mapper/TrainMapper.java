package org.pl.deenes.infrastructure.mapper;

import org.mapstruct.Condition;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.pl.deenes.infrastructure.entity.TrainEntity;
import org.pl.deenes.model.Train;

@Mapper(componentModel = "spring")
public interface TrainMapper {

    Train mapFromEntity(TrainEntity entity,
                        @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @InheritInverseConfiguration
    TrainEntity mapToEntity(Train lineDetails,
                            @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Condition
    default boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }
    @DoIgnore
    default TrainEntity mapToEntity(Train analyse) {
        return mapToEntity(analyse, new CycleAvoidingMappingContext());
    }

}

