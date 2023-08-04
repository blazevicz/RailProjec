package org.pl.deenes.infrastructure.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LineEntityTest {
    @Test
    void testGetterAndSetter() {
        LineEntity lineEntity = new LineEntity();

        int lineId = 1;
        int lineNumber = 123;
        String relationFrom = "From Station";
        String relationTo = "To Station";
        LineDetailsEntity lineDetailsEntity = new LineDetailsEntity();
        lineDetailsEntity.setLineDetailsId(1);

        lineEntity.setLineId(lineId);
        lineEntity.setLineNumber(lineNumber);
        lineEntity.setRelationFrom(relationFrom);
        lineEntity.setRelationTo(relationTo);
        lineEntity.setLineEntry(lineDetailsEntity);

        assertEquals(lineId, lineEntity.getLineId());
        assertEquals(lineNumber, lineEntity.getLineNumber());
        assertEquals(relationFrom, lineEntity.getRelationFrom());
        assertEquals(relationTo, lineEntity.getRelationTo());
        assertEquals(lineDetailsEntity, lineEntity.getLineEntry());
    }

    @Test
    void testToString() {
        int lineId = 1;
        int lineNumber = 123;
        String relationFrom = "From Station";
        String relationTo = "To Station";
        LineDetailsEntity lineDetailsEntity = new LineDetailsEntity();
        lineDetailsEntity.setLineDetailsId(1);

        LineEntity lineEntity = new LineEntity(
                lineId, lineNumber, relationFrom, relationTo, lineDetailsEntity
        );

        String expectedToString = "LineEntity(lineId=1, lineNumber=123, relationFrom=From Station, " +
                "relationTo=To Station, lineEntry=LineDetailsEntity(lineDetailsId=1, startStation=null, " +
                "endStation=null, lineNumber=null, startKilometer=null, endKilometer=null, page=null, " +
                "railwayRegion=null))";

        assertEquals(expectedToString, lineEntity.toString());
    }
}