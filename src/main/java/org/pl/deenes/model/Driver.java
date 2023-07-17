package org.pl.deenes.model;

import org.pl.deenes.infrastructure.entity.LineEntity;

import java.util.Set;

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
