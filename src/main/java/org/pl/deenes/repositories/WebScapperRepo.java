package org.pl.deenes.repositories;

import org.pl.deenes.data.ZLK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface WebScapperRepo extends JpaRepository<ZLK, Integer> {


    @Modifying
    @Query("update ZLK z set z.actualWOS = :actualWOS, z.actualWOSlink = :actualWOSlink where z.zlkRegionNumber = :zlkRegionNumber")
    void updateByZlkRegionNumber(@Param("zlkRegionNumber") Integer zlkRegionNumber, @Param("actualWOS") String actualWOS, @Param("actualWOSlink") String actualWOSlink);
}
