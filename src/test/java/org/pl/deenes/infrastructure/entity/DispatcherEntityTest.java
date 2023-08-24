package org.pl.deenes.infrastructure.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class DispatcherEntityTest {
    @Test
    void testDispatcherEntityConstructorAndGetters() {

        int dispatcherId = 1;
        String name = "Jan";
        String surname = "Kowalski";
        String phoneNumber = "123456789";

        DispatcherEntity dispatcherEntity =
                new DispatcherEntity(dispatcherId, name, surname, phoneNumber, "123", Boolean.TRUE, Set.of());

        Assertions.assertThat(dispatcherEntity.getDispatcherId()).isEqualTo(dispatcherId);
        Assertions.assertThat(dispatcherEntity.getName()).isEqualTo(name);
        Assertions.assertThat(dispatcherEntity.getSurname()).isEqualTo(surname);
        Assertions.assertThat(dispatcherEntity.getPhoneNumber()).isEqualTo(phoneNumber);
    }

    @Test
    void testDispatcherEntitySetters() {
        DispatcherEntity dispatcherEntity =
                new DispatcherEntity();

        int dispatcherId = 1;
        String name = "Jan";
        String surname = "Kowalski";
        String phoneNumber = "123456789";

        dispatcherEntity.setDispatcherId(dispatcherId);
        dispatcherEntity.setName(name);
        dispatcherEntity.setSurname(surname);
        dispatcherEntity.setPhoneNumber(phoneNumber);

        Assertions.assertThat(dispatcherEntity.getDispatcherId()).isEqualTo(dispatcherId);
        Assertions.assertThat(dispatcherEntity.getName()).isEqualTo(name);
        Assertions.assertThat(dispatcherEntity.getSurname()).isEqualTo(surname);
        Assertions.assertThat(dispatcherEntity.getPhoneNumber()).isEqualTo(phoneNumber);
    }

    @Test
    void testDispatcherEntityEqualsAndHashCode() {
        DispatcherEntity dispatcherEntity1
                = new DispatcherEntity(1, "Jan", "Kowalski", "123456789", "123", Boolean.TRUE, Set.of());
        DispatcherEntity dispatcherEntity2
                = new DispatcherEntity(1, "Jan", "Kowalski", "123456789", "123", Boolean.TRUE, Set.of());

        Assertions.assertThat(dispatcherEntity1).isEqualTo(dispatcherEntity2);
        Assertions.assertThat(dispatcherEntity1.hashCode()).hasSameHashCodeAs(dispatcherEntity2.hashCode());

        DispatcherEntity dispatcherEntity3 =
                new DispatcherEntity(2, "Robert", "Malinowski", "987654321", "123", Boolean.TRUE, Set.of());

        Assertions.assertThat(dispatcherEntity1).isNotEqualTo(dispatcherEntity3);
        Assertions.assertThat(dispatcherEntity1.hashCode()).isNotEqualTo(dispatcherEntity3.hashCode());
    }
}

