package org.pl.deenes.infrastructure.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pl.deenes.infrastructure.entity.DriverEntity;
import org.pl.deenes.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
                .surname("Test")
                .driverId(1)
                .pesel("Test")
                .password("Test")
                .build();
        var driver = driverMapper.mapFromEntity(test);

        assertEquals("Test", driver.getName());
        assertEquals("Test", driver.getSurname());
        assertEquals(1, driver.getDriverId());
        assertEquals("Test", driver.getPesel());
        assertEquals("Test", driver.getPassword());
    }

    @Test
    void mapToEntity() {
        var test = Driver.builder()
                .name("Test")
                .surname("Test")
                .driverId(1)
                .pesel("Test")
                .password("Test")
                .build();

        var driver = driverMapper.mapToEntity(test);
        assertEquals("Test", driver.getName());
        assertEquals("Test", driver.getSurname());
        assertEquals(1, driver.getDriverId());
        assertEquals("Test", driver.getPesel());
        assertEquals("Test", driver.getPassword());
    }
}