package org.pl.deenes.infrastructure.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TerrainProfileServiceEntityTest {
    @Test
    void testGetterAndSetter() {
        TerrainProfileEntity terrainProfileEntity = new TerrainProfileEntity();

        double kilometer = 100.0;
        double height = 200.0;
        LineEntity lineEntity = new LineEntity();
        lineEntity.setLineId(1);

        terrainProfileEntity.setKilometer(kilometer);
        terrainProfileEntity.setHeight(height);

        assertEquals(kilometer, terrainProfileEntity.getKilometer());
        assertEquals(height, terrainProfileEntity.getHeight());
    }

    @Test
    void testEquals() {
        var line = new LineEntity();
        TerrainProfileEntity terrainProfileEntity1 = TerrainProfileEntity.builder()
                .kilometer(100.0)
                .height(200.0)
                .build();

        TerrainProfileEntity terrainProfileEntity2 = TerrainProfileEntity.builder()
                .kilometer(100.0)
                .height(200.0)
                .build();

        boolean expectedEquals = true;

        assertEquals(expectedEquals, terrainProfileEntity1.equals(terrainProfileEntity2));
    }
}