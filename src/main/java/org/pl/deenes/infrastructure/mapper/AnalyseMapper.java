package org.pl.deenes.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.pl.deenes.infrastructure.entity.AnalyseEntity;
import org.pl.deenes.model.Analyse;

@Mapper(componentModel = "spring")
public interface AnalyseMapper {
//    @Mapping(source = "trainStatsEntity", target = "trainStats")
    Analyse mapFromEntity(AnalyseEntity entity);

    //  @Mapping(source = "trainStats", target = "trainStatsEntity")
    AnalyseEntity mapToEntity(Analyse analyse);
}
