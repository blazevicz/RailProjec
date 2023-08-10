package org.pl.deenes.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.pl.deenes.osm.model.GetMapDataByBoundingBox200ResponseElementsInner;
import org.pl.deenes.osm.model.Node;
import org.pl.deenes.osm.model.Relation;
import org.pl.deenes.osm.model.Way;

@Mapper(componentModel = "spring")
public interface NodeMapper {
    Node mapFromEntity(GetMapDataByBoundingBox200ResponseElementsInner node);

    Way mapFromEntityToWay(GetMapDataByBoundingBox200ResponseElementsInner node);

    Relation mapFromEntitytoRelation(GetMapDataByBoundingBox200ResponseElementsInner node);

}
