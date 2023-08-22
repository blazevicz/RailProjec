package org.pl.deenes.infrastructure.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pl.deenes.infrastructure.entity.RegionEntity;
import org.pl.deenes.infrastructure.mapper.RegionMapper;
import org.pl.deenes.infrastructure.repositories.jpa.RegionJpaRepository;
import org.pl.deenes.model.Region;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class RegionRepositoryTest {

    @Mock
    private RegionJpaRepository repository;

    @Mock
    private RegionMapper regionMapper;

    @InjectMocks
    private RegionRepository regionRepository;

    @Test
    void testSave() {
        Region region = Region.builder().regionId(1).zlkRegionNumber(1).actualWOSlink("a").actualWOS("b").build();
        RegionEntity regionEntity = new RegionEntity();

        when(regionMapper.mapToEntity(region)).thenReturn(regionEntity);
        when(repository.save(regionEntity)).thenReturn(regionEntity);
        when(regionMapper.mapFromEntity(regionEntity)).thenReturn(region);

        Region savedRegion = regionRepository.save(region);

        assertEquals(region, savedRegion);
        verify(regionMapper, times(1)).mapToEntity(region);
        verify(repository, times(1)).save(regionEntity);
        verify(regionMapper, times(1)).mapFromEntity(regionEntity);
    }

    @Test
    void testFindByZlkRegionNumber() {
        Integer regionNumber = 123;
        RegionEntity regionEntity = new RegionEntity();
        Region expectedRegion = Region.builder().regionId(1).zlkRegionNumber(1).actualWOSlink("a").actualWOS("b").build();

        when(repository.findByZlkRegionNumber(regionNumber)).thenReturn(Optional.of(regionEntity));
        when(regionMapper.mapFromEntity(regionEntity)).thenReturn(expectedRegion);

        Optional<Region> resultRegion = regionRepository.findByZlkRegionNumber(regionNumber);

        assertEquals(expectedRegion, resultRegion.get());
        verify(repository, times(1)).findByZlkRegionNumber(regionNumber);
        verify(regionMapper, times(1)).mapFromEntity(regionEntity);
    }

    @Test
    void testFindAll() {
        List<RegionEntity> regionEntities = List.of(new RegionEntity());
        List<Region> expectedRegions = List.of(Region.builder().regionId(1).zlkRegionNumber(1).actualWOSlink("a").actualWOS("b").build());

        when(repository.findAll()).thenReturn(regionEntities);
        when(regionMapper.mapFromEntity(any(RegionEntity.class))).thenReturn(expectedRegions.get(0));

        List<Region> resultRegions = regionRepository.findAll();

        assertEquals(expectedRegions, resultRegions);
        verify(repository, times(1)).findAll();
        verify(regionMapper, times(regionEntities.size())).mapFromEntity(any(RegionEntity.class));
    }
}
