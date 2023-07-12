package org.pl.deenes.infrastructure.repositories;

import org.pl.deenes.infrastructure.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface WebScapperRepo extends JpaRepository<RegionEntity, Integer> {


    @Modifying
    //@Query("update RegionEntity z set z.actualWOS = :actualWOS, z.actualWOSlink = :actualWOSlink where z.zlkRegionNumber = :zlkRegionNumber and z.actualWOS != :actualWOS")
    @Query("update RegionEntity z set z.actualWOS = :actualWOS, z.actualWOSlink = :actualWOSlink where z.zlkRegionNumber = :zlkRegionNumber and z.actualWOS != :actualWOS")
    void updateByZlkRegionNumber(@Param("zlkRegionNumber") Integer zlkRegionNumber, @Param("actualWOS") String actualWOS, @Param("actualWOSlink") String actualWOSlink);
}
