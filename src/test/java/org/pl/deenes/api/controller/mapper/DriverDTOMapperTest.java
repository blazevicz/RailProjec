package org.pl.deenes.api.controller.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pl.deenes.api.controller.dto.DriverDTO;
import org.pl.deenes.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        DriverDTOMapperImpl.class
})
class DriverDTOMapperTest {

    @Autowired
    private DriverDTOMapper driverDTOMapper;

    @Test
    void mapToDTO() {

        Driver test = Driver.builder()
                .name("Test")
                .build();
        DriverDTO driverDTO = driverDTOMapper.mapToDTO(test);

        Assertions.assertEquals("Test", driverDTO.getName());
    }

    @Test
    void mapFromDTO() {

        DriverDTO test = DriverDTO.builder()
                .name("Test")
                .build();

        Driver driver = driverDTOMapper.mapFromDTO(test);
        Assertions.assertEquals("Test", driver.getName());
    }
}