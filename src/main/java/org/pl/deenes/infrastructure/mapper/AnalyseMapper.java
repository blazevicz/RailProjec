package org.pl.deenes.infrastructure.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.pl.deenes.infrastructure.entity.AnalyseEntity;
import org.pl.deenes.model.Analyse;

@Mapper(componentModel = "spring")
public interface AnalyseMapper {
    @Mapping(target = "trainStats", ignore = true)
    @Named("fromEntity")
    Analyse mapFromEntity(AnalyseEntity entity,
                          @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "trainStats", ignore = true)
    @Named("toEntity")
    AnalyseEntity mapToEntity(Analyse analyse,
                              @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @DoIgnore
    default AnalyseEntity mapToEntity(Analyse analyse) {
        return mapToEntity(analyse, new CycleAvoidingMappingContext());
    }


}
