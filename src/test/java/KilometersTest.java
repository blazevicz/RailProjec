import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pl.deenes.services.Kilometers;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class KilometersTest {
    private List<List<String>> allKilometers;
    private Kilometers kilometers;

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

        kilometers = new Kilometers();
    }

    @Test
    void testGiveAllKilometers() {
        kilometers.setAllKilometers(List.of(
                List.of("1", "2.5", "3.75", "5.1"),
                List.of("2", "3", "4.5", "6.9", "8.7", "10")
        ));
        kilometers.giveAllKilometers();
        List<Number> expectedLineNumbers = List.of(1, 2.5, 3.75, 5.1, 2, 3, 4.5, 6.9, 8.7, 10);
        assertEquals(expectedLineNumbers, kilometers.getLineNumbers());
    }


    @Test
    void testIsNumeric() {
        // given
        String numericString = "1234";
        String notNumericString = "1234a";

        // when
        boolean isNumeric1 = Kilometers.isNumeric(numericString);
        boolean isNumeric2 = Kilometers.isNumeric(notNumericString);

        // then
        assertTrue(isNumeric1);
        Assertions.assertFalse(isNumeric2);
    }

    @Test
    void testGetAllRailwayLines() {
        kilometers.setAllKilometers(List.of(
                List.of("1", "2.5", "3.75", "5.1"),
                List.of("2", "3", "4.5", "6.9", "8.7", "10")
        ));
        kilometers.getAllRailwayLines();
        Set<Integer> expectedKilometersAfterConvert = Set.of(1, 2, 3, 10);
        assertEquals(expectedKilometersAfterConvert, kilometers.getKilometersAfterConvert());
    }

    @Test
    void testGiveAllKilometersParseException() {
        Kilometers kilometers = new Kilometers();
        kilometers.setAllKilometers(List.of(
                List.of("1", "2.5", "3.75", "5.1"),
                List.of("invalid", "3", "4.5", "6.9", "8.7", "10")
        ));
        kilometers.giveAllKilometers();
        List<Number> expectedLineNumbers = List.of(1, 2.5, 3.75, 5.1, 3, 4.5, 6.9, 8.7, 10);
        assertEquals(expectedLineNumbers, kilometers.getLineNumbers());
    }

    @Test
    void testGiveAllKilometersHandlesNullValues() {
        // given
        List<List<String>> allKilometers = new ArrayList<>();
        allKilometers.add(Arrays.asList("2", null, "13.2", "18.5"));
        allKilometers.add(Arrays.asList("6.1", "12", null, "23.4"));

        Kilometers kilometers = new Kilometers();
        kilometers.setAllKilometers(allKilometers);

        // when
        kilometers.giveAllKilometers();
        List<Number> result = kilometers.getLineNumbers();

        // then
        List<Number> expected = Arrays.asList(2, 13.2, 18.5, 6.1, 12, 23.4);
        assertEquals(expected, result);
    }

    @Test
    void testGiveAllKilometersHandlesInvalidNumberFormat() {
        // given
        List<List<String>> allKilometers = new ArrayList<>();
        allKilometers.add(Arrays.asList("2", "5.7", "abc", "18.5"));
        allKilometers.add(Arrays.asList("6.1", "12", "19.8", "xyz"));

        Kilometers kilometers = new Kilometers();
        kilometers.setAllKilometers(allKilometers);

        // when
        kilometers.giveAllKilometers();
        List<Number> result = kilometers.getLineNumbers();

        // then
        List<Number> expected = Arrays.asList(2, 5.7, 18.5, 6.1, 12, 19.8);
        assertEquals(expected, result);
    }

    @Test
    void testGiveAllKilometersHandlesEmptyList() {
        // given
        Kilometers kilometers = new Kilometers();
        kilometers.setAllKilometers(Collections.emptyList());

        // when
        kilometers.giveAllKilometers();
        assertTrue(kilometers.getLineNumbers().isEmpty());
    }
}
