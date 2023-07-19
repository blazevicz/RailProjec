package org.pl.deenes.infrastructure.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pl.deenes.infrastructure.entity.DriverEntity;
import org.pl.deenes.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        DriverMapperImpl.class
})
class DriverMapperTest {

    @Autowired
    private DriverMapper driverMapper;

    @Test
    void mapFromEntity() {
        DriverEntity test = DriverEntity.builder()
                .name("Test")
                .build();
        var driver = driverMapper.mapFromEntity(test);

        Assertions.assertEquals("Test", driver.name());
    }

    @Test
    void mapToEntity() {
        var test = Driver.builder()
                .name("Test")
                .build();

        var driver = driverMapper.mapToEntity(test);
        Assertions.assertEquals("Test", driver.getName());
    }
}