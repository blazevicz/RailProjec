package org.pl.deenes.infrastructure.repositories;

import org.pl.deenes.infrastructure.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZLKRepo extends JpaRepository<RegionEntity, Long> {
}
