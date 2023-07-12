package org.pl.deenes.model;

import java.util.List;

public record Dispatcher(
        Integer id,
        String name,
        String surname,
        String phoneNumber,
        Integer pesel,
        List<Train> trains) {
}
