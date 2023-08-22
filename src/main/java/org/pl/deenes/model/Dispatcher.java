package org.pl.deenes.model;

import lombok.Builder;
import org.pl.deenes.infrastructure.entity.RoleEntity;

import java.util.List;
import java.util.Set;

@Builder
public record Dispatcher(
        Integer id,
        String name,
        String surname,
        String phoneNumber,
        Boolean active,
        Set<RoleEntity> roles,
        String password,
        Long pesel,
        List<Train> trains) {
}
