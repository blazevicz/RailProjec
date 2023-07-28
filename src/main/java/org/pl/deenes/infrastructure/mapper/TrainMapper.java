package org.pl.deenes.infrastructure.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.pl.deenes.infrastructure.entity.TrainEntity;
import org.pl.deenes.model.Train;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TrainMapper {
    // @Mapping(target = "lineList", ignore = true)
    //  @Mapping(target = "driver", ignore = true)
    // @Mapping(target = "analyse", ignore = true)
    Train mapFromEntity(TrainEntity entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    //@Mapping(target = "line", ignore = true)
    //@Mapping(target = "driver", ignore = true)
    //@Mapping(target = "analyse", ignore = true)
    TrainEntity mapToEntity(Train lineDetails, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);


    @DoIgnore
    default TrainEntity mapToEntity(Train analyse) {
        return mapToEntity(analyse, new CycleAvoidingMappingContext());
    }

}

