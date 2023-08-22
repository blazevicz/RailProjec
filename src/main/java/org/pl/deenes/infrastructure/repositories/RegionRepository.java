package org.pl.deenes.infrastructure.repositories;

import lombok.AllArgsConstructor;
import org.pl.deenes.infrastructure.entity.RegionEntity;
import org.pl.deenes.infrastructure.mapper.RegionMapper;
import org.pl.deenes.infrastructure.repositories.dao.RegionDAO;
import org.pl.deenes.infrastructure.repositories.jpa.RegionJpaRepository;
import org.pl.deenes.model.Region;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@AllArgsConstructor
public class RegionRepository implements RegionDAO {

    private final RegionJpaRepository repository;
    private final RegionMapper regionMapper;

    @Override
    public Region save(Region region) {
        RegionEntity regionEntity = regionMapper.mapToEntity(region);
        RegionEntity save = repository.save(regionEntity);
        return regionMapper.mapFromEntity(save);
    }

    @Override
    public Optional<Region> findByZlkRegionNumber(Integer regionNumber) {
        var byZlkRegionNumber = repository.findByZlkRegionNumber(regionNumber);

        return byZlkRegionNumber.map(regionMapper::mapFromEntity);
    }

    @Override
    public List<Region> findAll() {
        List<RegionEntity> all = repository.findAll();
        return all.stream().map(regionMapper::mapFromEntity).toList();
    }
}

