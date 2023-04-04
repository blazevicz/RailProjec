import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pl.deenes.services.KilometersServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class KilometersServiceImplTest {
    private List<List<String>> allKilometers;
    private KilometersServiceImpl kilometersServiceImpl;

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

        kilometersServiceImpl = new KilometersServiceImpl();
    }

    @Test
    void testGiveAllKilometers() {
        kilometersServiceImpl.setAllKilometers(List.of(
                List.of("1", "2.5", "3.75", "5.1"),
                List.of("2", "3", "4.5", "6.9", "8.7", "10")
        ));
        kilometersServiceImpl.giveAllKilometers();
        List<Number> expectedLineNumbers = List.of(1, 2.5, 3.75, 5.1, 2, 3, 4.5, 6.9, 8.7, 10);
        assertEquals(expectedLineNumbers, kilometersServiceImpl.getLineNumbers());
    }


    @Test
    void testIsNumeric() {
        // given
        String numericString = "1234";
        String notNumericString = "1234a";

        // when
        boolean isNumeric1 = KilometersServiceImpl.isNumeric(numericString);
        boolean isNumeric2 = KilometersServiceImpl.isNumeric(notNumericString);

        // then
        assertTrue(isNumeric1);
        Assertions.assertFalse(isNumeric2);
    }

    @Test
    void testGetAllRailwayLines() {
        kilometersServiceImpl.setAllKilometers(List.of(
                List.of("1", "2.5", "3.75", "5.1"),
                List.of("2", "3", "4.5", "6.9", "8.7", "10")
        ));
        kilometersServiceImpl.getAllRailwayLines();
        Set<Integer> expectedKilometersAfterConvert = Set.of(1, 2, 3, 10);
        assertEquals(expectedKilometersAfterConvert, kilometersServiceImpl.getKilometersAfterConvert());
    }

    @Test
    void testGiveAllKilometersParseException() {
        KilometersServiceImpl kilometersServiceImpl = new KilometersServiceImpl();
        kilometersServiceImpl.setAllKilometers(List.of(
                List.of("1", "2.5", "3.75", "5.1"),
                List.of("invalid", "3", "4.5", "6.9", "8.7", "10")
        ));
        kilometersServiceImpl.giveAllKilometers();
        List<Number> expectedLineNumbers = List.of(1, 2.5, 3.75, 5.1, 3, 4.5, 6.9, 8.7, 10);
        assertEquals(expectedLineNumbers, kilometersServiceImpl.getLineNumbers());
    }

    @Test
    void testGiveAllKilometersHandlesNullValues() {
        // given
        List<List<String>> allKilometers = new ArrayList<>();
        allKilometers.add(Arrays.asList("2", null, "13.2", "18.5"));
        allKilometers.add(Arrays.asList("6.1", "12", null, "23.4"));

        KilometersServiceImpl kilometersServiceImpl = new KilometersServiceImpl();
        kilometersServiceImpl.setAllKilometers(allKilometers);

        // when
        kilometersServiceImpl.giveAllKilometers();
        List<Number> result = kilometersServiceImpl.getLineNumbers();

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

        KilometersServiceImpl kilometersServiceImpl = new KilometersServiceImpl();
        kilometersServiceImpl.setAllKilometers(allKilometers);

        // when
        kilometersServiceImpl.giveAllKilometers();
        List<Number> result = kilometersServiceImpl.getLineNumbers();

        // then
        List<Number> expected = Arrays.asList(2, 5.7, 18.5, 6.1, 12, 19.8);
        assertEquals(expected, result);
    }

    @Test
    void testGiveAllKilometersHandlesEmptyList() {
        // given
        KilometersServiceImpl kilometersServiceImpl = new KilometersServiceImpl();
        kilometersServiceImpl.setAllKilometers(Collections.emptyList());

        // when
        kilometersServiceImpl.giveAllKilometers();
        assertTrue(kilometersServiceImpl.getLineNumbers().isEmpty());
    }
}
