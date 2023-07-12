package org.pl.deenes.model;

import org.pl.deenes.infrastructure.entity.LineEntity;

import java.util.List;

public record Driver(
        Integer id,
        String name,
        String surname,
        Integer pesel,

        List<LocomotiveType> locomotiveAuthorization,
        List<LineEntity> lineAuthorization,
        List<Train> trains
) {

}
