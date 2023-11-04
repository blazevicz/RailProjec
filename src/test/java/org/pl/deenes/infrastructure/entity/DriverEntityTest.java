package org.pl.deenes.infrastructure.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class DriverEntityTest {

    @Test
    void testDriverEntityConstructorAndGetters() {
        int driverId = 1;
        String name = "Jan";
        String surname = "Kowalski";
        String pesel = "66071749125";

        var driverEntity = DriverEntity.builder()
                .driverId(driverId)
                .name(name)
                .surname(surname)
                .pesel(pesel)
                .active(true)
                .roles(Set.of())
                .build();

        Assertions.assertThat(driverEntity.getDriverId()).isEqualTo(driverId);
        Assertions.assertThat(driverEntity.getName()).isEqualTo(name);
        Assertions.assertThat(driverEntity.getSurname()).isEqualTo(surname);
        Assertions.assertThat(driverEntity.getPesel()).isEqualTo(pesel);
    }

    @Test
    void testDriverEntitySetters() {
        DriverEntity driverEntity = new DriverEntity();

        int driverId = 1;
        String name = "Jan";
        String surname = "Kowalski";
        String pesel = "66071749125";

        driverEntity.setDriverId(driverId);
        driverEntity.setName(name);
        driverEntity.setSurname(surname);
        driverEntity.setPesel(pesel);

        Assertions.assertThat(driverEntity.getDriverId()).isEqualTo(driverId);
        Assertions.assertThat(driverEntity.getName()).isEqualTo(name);
        Assertions.assertThat(driverEntity.getSurname()).isEqualTo(surname);
        Assertions.assertThat(driverEntity.getPesel()).isEqualTo(pesel);
    }
}
