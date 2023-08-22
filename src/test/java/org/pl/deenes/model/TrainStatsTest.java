package org.pl.deenes.model;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TrainStatsTest {
    @Test
    void testEqualsAndHashCode() {
        LinkedList<Line> lineList = new LinkedList<>();
        Line line1 = new Line(1, List.of());
        Line line2 = new Line(2, List.of());
        lineList.add(line1);
        lineList.add(line2);

        TrainStats stats1 = TrainStats.builder()
                .trainStatsId(1)
                .firstKilometer(100.0)
                .lastKilometer(200.0)
                .lineNumber(1)
                .lineList(lineList)
                .build();

        TrainStats stats2 = TrainStats.builder()
                .trainStatsId(1)
                .firstKilometer(100.0)
                .lastKilometer(200.0)
                .lineNumber(1)
                .lineList(lineList)
                .build();

        assertEquals(stats1, stats2);
        assertEquals(stats1.hashCode(), stats2.hashCode());
    }

    @Test
    void testNotEquals() {
        LinkedList<Line> lineList1 = new LinkedList<>();
        Line line1 = new Line(1, List.of());
        Line line2 = new Line(2, List.of());
        lineList1.add(line1);
        lineList1.add(line2);

        LinkedList<Line> lineList2 = new LinkedList<>();
        Line line3 = new Line(3, List.of());
        lineList2.add(line3);

        TrainStats stats1 = TrainStats.builder()
                .trainStatsId(1)
                .firstKilometer(100.0)
                .lastKilometer(200.0)
                .lineNumber(1)
                .lineList(lineList1)
                .build();

        TrainStats stats2 = TrainStats.builder()
                .trainStatsId(2)
                .firstKilometer(150.0)
                .lastKilometer(250.0)
                .lineNumber(1)
                .lineList(lineList2)
                .build();

        assertNotEquals(stats1, stats2);
    }

    @Test
    void testToString() {
        TrainStats stats = TrainStats.builder()
                .trainStatsId(1)
                .firstKilometer(100.0)
                .lastKilometer(200.0)
                .lineNumber(1)
                .build();

        String expectedToString = "TrainStats(trainStatsId=1, station=null, lineNumber=1, firstKilometer=100.0, lastKilometer=200.0)";
        assertEquals(expectedToString, stats.toString());
    }
}
