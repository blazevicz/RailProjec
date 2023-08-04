package org.pl.deenes.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pl.deenes.infrastructure.repositories.TerrainProfileRepository;
import org.pl.deenes.model.TerrainProfile;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TerrainProfileServiceImplTest {

    @Mock
    private TerrainProfileRepository terrainProfileJpaRepository;

    @InjectMocks
    private TerrainProfileServiceImpl terrainProfileService;

    @Test
    void testCalculateSlope_EmptyList() {
        when(terrainProfileJpaRepository.findAllByLineNumberIsAndKilometerBetween(anyInt(), anyDouble(), anyDouble()))
                .thenReturn(new LinkedList<>());

        List<TerrainProfile> result = terrainProfileService.calculateSlope(1, 0.0, 10.0);

        assertTrue(result.isEmpty());
    }

    @Test
    void testCalculateSlope_SingleElementList() {
        TerrainProfile build = TerrainProfile.builder()
                .height(15.5)
                .kilometer(22)
                .lineNumber(2)
                .build();

        LinkedList<TerrainProfile> singleElementList = new LinkedList<>(List.of(build));
        when(terrainProfileJpaRepository.findAllByLineNumberIsAndKilometerBetween(anyInt(), anyDouble(), anyDouble()))
                .thenReturn((LinkedList<TerrainProfile>) singleElementList);

        List<TerrainProfile> result = terrainProfileService.calculateSlope(1, 0.0, 10.0);

        assertEquals(singleElementList, result);
        assertEquals(0.0, build.getSlope());
    }

    @Test
    void testCalculateSlope_MultipleElementsList() {
        TerrainProfile build1 = TerrainProfile.builder()
                .height(15.5)
                .kilometer(22)
                .lineNumber(2)
                .build();
        TerrainProfile build2 = TerrainProfile.builder()
                .height(15.5)
                .kilometer(22)
                .lineNumber(2)
                .build();

        LinkedList<TerrainProfile> multipleElementsList = new LinkedList<>(List.of(build1, build2));
        when(terrainProfileJpaRepository.findAllByLineNumberIsAndKilometerBetween(anyInt(), anyDouble(), anyDouble()))
                .thenReturn((LinkedList<TerrainProfile>) multipleElementsList);

        List<TerrainProfile> result = terrainProfileService.calculateSlope(1, 0.0, 10.0);

        assertEquals(multipleElementsList, result);
        assertEquals(0.0, build1.getSlope());
        assertEquals(build1.getSlope(), build2.getSlope());
    }
}
