package org.pl.deenes.infrastructure.repositories.jpa;

import org.pl.deenes.infrastructure.entity.RegionEntity;
import org.pl.deenes.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionJpaRepository extends JpaRepository<RegionEntity, Integer> {
    List<RegionEntity> findAll();
    RegionEntity save(Region region);
    Optional<RegionEntity> findByZlkRegionNumber(Integer regionNumber);
    @Modifying
    @Query("update RegionEntity z set z.actualWOS = :actualWOS, z.actualWOSlink = :actualWOSlink where z.zlkRegionNumber = :zlkRegionNumber and z.actualWOS != :actualWOS")
    void updateByZlkRegionNumber(@Param("zlkRegionNumber") Integer zlkRegionNumber, @Param("actualWOS") String actualWOS, @Param("actualWOSlink") String actualWOSlink);
}
