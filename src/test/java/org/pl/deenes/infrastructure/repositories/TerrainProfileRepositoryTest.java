package org.pl.deenes.infrastructure.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pl.deenes.infrastructure.entity.TerrainProfileEntity;
import org.pl.deenes.infrastructure.mapper.TerrainProfileMapper;
import org.pl.deenes.infrastructure.repositories.jpa.TerrainProfileJpaRepository;
import org.pl.deenes.model.TerrainProfile;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TerrainProfileRepositoryTest {

    @Mock
    private TerrainProfileJpaRepository terrainProfileJpaRepository;

    @Mock
    private TerrainProfileMapper terrainProfileMapper;

    @InjectMocks
    private TerrainProfileRepository terrainProfileRepository;

    @Test
    void findAllByLineNumberIsAndKilometerBetween() {
        Integer lineNumber = 123;
        Double kilometerA = 10.0;
        Double kilometerB = 20.0;

        LinkedList<TerrainProfileEntity> terrainProfiles = new LinkedList<>(List.of(TerrainProfileEntity.builder().build()));

        when(terrainProfileJpaRepository.findAllByLineNumberIsAndKilometerBetween(lineNumber, kilometerA, kilometerB))
                .thenReturn(terrainProfiles);
        when(terrainProfileMapper.mapFromEntity(any()))
                .thenReturn(TerrainProfile.builder().lineNumber(lineNumber).kilometer(1.1).build());

        List<TerrainProfile> resultProfiles = terrainProfileRepository
                .findAllByLineNumberIsAndKilometerBetween(lineNumber, kilometerA, kilometerB);

        assertEquals(terrainProfiles.size(), resultProfiles.size());
    }
}
