package org.pl.deenes.model;

import lombok.Builder;
import org.pl.deenes.infrastructure.entity.LineEntity;

import java.util.Set;

@Builder
public record Driver(
        Integer id,
        String name,
        String surname,
        Integer pesel,

        Set<LocomotiveType> locomotiveAuthorization,
        Set<LineEntity> lineAuthorization,
        Set<Train> trains
) {

}
