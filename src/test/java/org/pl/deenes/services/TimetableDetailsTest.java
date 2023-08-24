package org.pl.deenes.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TimetableDetailsTest {
    private List<List<String>> allKilometers;
    private TimetableDetails timetableDetails;

    @BeforeEach
    public void setUp() {
        allKilometers = new ArrayList<>();
        List<String> kilometersList1 = new ArrayList<>();
        kilometersList1.add("1");
        kilometersList1.add("2");
        List<String> kilometersList2 = new ArrayList<>();
        kilometersList2.add("3");
        kilometersList2.add("4");
        allKilometers.add(kilometersList1);
        allKilometers.add(kilometersList2);

        timetableDetails = new TimetableDetails();
    }

    @Test
    void testGiveAllKilometers() {
        timetableDetails.setAllKilometers(List.of(
                List.of("1", "2.5", "3.75", "5.1"),
                List.of("2", "3", "4.5", "6.9", "8.7", "10")
        ));
        timetableDetails.giveAllKilometers();
        List<Number> expectedLineNumbers = List.of(1, 2.5, 3.75, 5.1, 2, 3, 4.5, 6.9, 8.7, 10);
        assertEquals(expectedLineNumbers, timetableDetails.getLineNumbers());
    }

    @Test
    void testIsNumeric() {
        String numericString = "1234";
        String notNumericString = "1234a";
        // when
        boolean isNumeric1 = TimetableDetails.isNumeric(numericString);
        boolean isNumeric2 = TimetableDetails.isNumeric(notNumericString);

        assertTrue(isNumeric1);
        Assertions.assertFalse(isNumeric2);
    }

    @Test
    void testGetAllRailwayLines() {
        timetableDetails.setAllKilometers(List.of(
                List.of("1", "2.5", "3.75", "5.1"),
                List.of("2", "3", "4.5", "6.9", "8.7", "10")
        ));
        timetableDetails.getAllRailwayLines();
        Set<Integer> expectedKilometersAfterConvert = Set.of(1, 2, 3, 10);
        assertEquals(expectedKilometersAfterConvert, timetableDetails.getKilometersAfterConvert());
    }

    @Test
    void testGiveAllKilometersParseException() {
        TimetableDetails timetableDetails = new TimetableDetails();
        timetableDetails.setAllKilometers(List.of(
                List.of("1", "2.5", "3.75", "5.1"),
                List.of("invalid", "3", "4.5", "6.9", "8.7", "10")
        ));
        timetableDetails.giveAllKilometers();
        List<Number> expectedLineNumbers = List.of(1, 2.5, 3.75, 5.1, 3, 4.5, 6.9, 8.7, 10);
        assertEquals(expectedLineNumbers, timetableDetails.getLineNumbers());
    }

    @Test
    void testGiveAllKilometersHandlesNullValues() {
        List<List<String>> allKilometers = new ArrayList<>();
        allKilometers.add(Arrays.asList("2", null, "13.2", "18.5"));
        allKilometers.add(Arrays.asList("6.1", "12", null, "23.4"));

        TimetableDetails timetableDetails = new TimetableDetails();
        timetableDetails.setAllKilometers(allKilometers);

        timetableDetails.giveAllKilometers();
        List<Number> result = timetableDetails.getLineNumbers();

        List<Number> expected = Arrays.asList(2, 13.2, 18.5, 6.1, 12, 23.4);
        assertEquals(expected, result);
    }

    @Test
    void testGiveAllKilometersHandlesInvalidNumberFormat() {
        List<List<String>> allKilometers = new ArrayList<>();
        allKilometers.add(Arrays.asList("2", "5.7", "abc", "18.5"));
        allKilometers.add(Arrays.asList("6.1", "12", "19.8", "xyz"));

        TimetableDetails timetableDetails = new TimetableDetails();
        timetableDetails.setAllKilometers(allKilometers);

        timetableDetails.giveAllKilometers();
        List<Number> result = timetableDetails.getLineNumbers();

        List<Number> expected = Arrays.asList(2, 5.7, 18.5, 6.1, 12, 19.8);
        assertEquals(expected, result);
    }

    @Test
    void testGiveAllKilometersHandlesEmptyList() {
        TimetableDetails timetableDetails = new TimetableDetails();
        timetableDetails.setAllKilometers(Collections.emptyList());

        timetableDetails.giveAllKilometers();
        assertTrue(timetableDetails.getLineNumbers().isEmpty());
    }
}
