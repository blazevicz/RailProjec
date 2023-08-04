package org.pl.deenes.model;

import lombok.Builder;

import java.util.List;

@Builder
public record Dispatcher(
        Integer id,
        String name,
        String surname,
        String phoneNumber,
        Integer pesel,
        List<Train> trains) {
}
