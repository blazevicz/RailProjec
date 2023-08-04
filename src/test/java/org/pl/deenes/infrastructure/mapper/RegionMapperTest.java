package org.pl.deenes.infrastructure.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pl.deenes.infrastructure.entity.RegionEntity;
import org.pl.deenes.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        RegionMapperImpl.class
})
class RegionMapperTest {

    @Autowired
    private RegionMapper regionMapper;

    @Test
    void mapFromEntity() {
        RegionEntity test = RegionEntity.builder()
                .zlkRegionNumber(1)
                .actualWOS("test")
                .actualWOSlink("link")
                .build();
        var region = regionMapper.mapFromEntity(test);

        Assertions.assertEquals(1, region.getZlkRegionNumber());
        Assertions.assertEquals("test", region.getActualWOS());
        Assertions.assertEquals("link", region.getActualWOSlink());
    }

    @Test
    void mapToEntity() {
        Region test = Region.builder()
                .zlkRegionNumber(1)
                .actualWOS("test")
                .actualWOSlink("link")
                .build();

        var regionEntity = regionMapper.mapToEntity(test);

        Assertions.assertEquals(1, regionEntity.getZlkRegionNumber());
        Assertions.assertEquals("test", regionEntity.getActualWOS());
        Assertions.assertEquals("link", regionEntity.getActualWOSlink());
    }


}
