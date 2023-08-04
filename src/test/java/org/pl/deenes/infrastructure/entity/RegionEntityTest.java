package org.pl.deenes.infrastructure.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegionEntityTest {
    @Test
    void testGetterAndSetter() {
        RegionEntity regionEntity = new RegionEntity();

        int regionId = 1;
        int zlkRegionNumber = 123;
        String actualWOS = "Actual WOS";
        String actualWOSlink = "www.google.com";
        LineEntity lineEntity = new LineEntity();
        lineEntity.setLineId(1);

        regionEntity.setRegionId(regionId);
        regionEntity.setZlkRegionNumber(zlkRegionNumber);
        regionEntity.setActualWOS(actualWOS);
        regionEntity.setActualWOSlink(actualWOSlink);
        //regionEntity.setLine(lineEntity);

        assertEquals(regionId, regionEntity.getRegionId());
        assertEquals(zlkRegionNumber, regionEntity.getZlkRegionNumber());
        assertEquals(actualWOS, regionEntity.getActualWOS());
        assertEquals(actualWOSlink, regionEntity.getActualWOSlink());
        //assertEquals(lineEntity, regionEntity.getLine());
    }

    @Test
    void testEquals() {
        var line = new LineEntity();
        RegionEntity regionEntity1 = RegionEntity.builder()
                .regionId(1)
                .zlkRegionNumber(123)
                .actualWOS("Actual WOS")
                .actualWOSlink("www.google.com")
                //  .line(line)
                .build();

        RegionEntity regionEntity2 = RegionEntity.builder()
                .regionId(1)
                .zlkRegionNumber(123)
                .actualWOS("Actual WOS")
                .actualWOSlink("www.google.com")
                // .line(line)
                .build();

        boolean expectedEquals = true;

        assertEquals(expectedEquals, regionEntity1.equals(regionEntity2));
    }
}