package org.pl.deenes.infrastructure.mapper;

import org.mapstruct.*;
import org.pl.deenes.infrastructure.entity.AnalyseEntity;
import org.pl.deenes.model.Analyse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnalyseMapper {
    @Named("fromEntity")
    Analyse mapFromEntity(AnalyseEntity entity,
                          @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @InheritInverseConfiguration
    AnalyseEntity mapToEntity(Analyse analyse,
                              @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Condition
    default boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }

    @DoIgnore
    default AnalyseEntity mapToEntity(Analyse analyse) {
        return mapToEntity(analyse, new CycleAvoidingMappingContext());
    }
}
