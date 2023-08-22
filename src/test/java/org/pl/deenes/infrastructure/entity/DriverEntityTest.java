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

        DriverEntity driverEntity = new DriverEntity(driverId, name, surname, pesel, "123", Boolean.TRUE, Set.of());

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

    @Test
    void testDriverEntityEqualsAndHashCode() {
        DriverEntity driverEntity1 = new DriverEntity(1, "Jan", "Kowalski", "66071749125L", "123", Boolean.TRUE, Set.of());
        DriverEntity driverEntity2 = new DriverEntity(1, "Jan", "Kowalski", "66071749125L", "123", Boolean.TRUE, Set.of());

        Assertions.assertThat(driverEntity1).isEqualTo(driverEntity2);
        Assertions.assertThat(driverEntity1.hashCode()).hasSameHashCodeAs(driverEntity2.hashCode());

        DriverEntity driverEntity3 = new DriverEntity(2, "Robert", "Adamczyk", "66071749125L", "123", Boolean.TRUE, Set.of());

        Assertions.assertThat(driverEntity1).isNotEqualTo(driverEntity3);
        Assertions.assertThat(driverEntity1.hashCode()).isNotEqualTo(driverEntity3.hashCode());
    }
}
