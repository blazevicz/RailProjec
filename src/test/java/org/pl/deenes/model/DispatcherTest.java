package org.pl.deenes.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DispatcherTest {
    @Test
    void testConstructorAndGetters() {
        Integer id = 1;
        String name = "Jan";
        String surname = "Kowalski";
        String phoneNumber = "123456789";
        Long pesel = 66071749125L;
        List<Train> trains = new ArrayList<>();

        Train build = Train.builder().trainId(1).build();
        Train build2 = Train.builder().trainId(2).build();
        trains.add(build);
        trains.add(build2);

        Dispatcher dispatcher =
                new Dispatcher(id, name, surname, phoneNumber, true, Set.of(), "asd", pesel, trains);

        assertNotNull(dispatcher);
        assertEquals(id, dispatcher.getId());
        assertEquals(name, dispatcher.getName());
        assertEquals(surname, dispatcher.getSurname());
        assertEquals(phoneNumber, dispatcher.getPhoneNumber());
        assertEquals(pesel, dispatcher.getPesel());
        assertEquals(trains, dispatcher.getTrains());
    }
}