package org.pl.deenes.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pl.deenes.model.Line;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CalculateTimetableDetailsTest {
    private final List<Number> lineNumbers = List.of(0.0, 5, 10.0, 15.5, 20, 25.5, 30.0, 35.5, 40, 45.5, 50.0, 55.5);
    @Mock
    private TimetableDetails kilometersService;

    @InjectMocks
    private CalculateKilometersServiceImpl calculateKilometersService;

    static Stream<Arguments> testCreateLinesAndAddToLineList() {
        return Stream.of(
                Arguments.of(List.of(0.0, 5, 10.0, 15.5, 20, 25.5, 30.0, 35.5, 40, 45.5, 50.0, 55.5)),
                Arguments.of(List.of(5, 10.0, 15.5, 20, 25.5, 30.0, 35.5, 40, 45.5, 50.0, 55.5)));
    }

    @ParameterizedTest
    @MethodSource
    void testCreateLinesAndAddToLineList(List<Number> numberList) {
        Mockito.when(kilometersService.getLineNumbers()).thenReturn(numberList);

        LinkedList<Line> expectedLines = new LinkedList<>();
        expectedLines.add(new Line(20, List.of(25.5, 30.0, 35.5)));
        expectedLines.add(new Line(5, List.of(10.0, 15.5)));
        expectedLines.add(new Line(40, List.of(45.5, 50.0, 55.5, 61.1)));

        LinkedList<Line> actualLines = calculateKilometersService.createLinesAndAddToLineList(61.1);

        assertThat(actualLines).isEqualTo(expectedLines);

    }

}
