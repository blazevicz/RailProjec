package org.pl.deenes.infrastructure.repositories.dao;

import org.pl.deenes.model.Region;

import java.util.List;
import java.util.Optional;

public interface RegionDAO {
    List<Region> findAll();

    Region save(Region region);

    Optional<Region> findByZlkRegionNumber(Integer regionNumber);


}
