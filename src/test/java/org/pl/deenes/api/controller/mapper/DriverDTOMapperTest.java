package org.pl.deenes.api.controller.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pl.deenes.api.controller.dto.DriverDTO;
import org.pl.deenes.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
                .surname("Test")
                .driverId(1)
                .pesel("Test")
                .password("Test")
                .build();
        DriverDTO driverDTO = driverDTOMapper.mapToDTO(test);

        assertEquals("Test", driverDTO.getName());
        assertEquals("Test", driverDTO.getSurname());
        assertEquals(1, driverDTO.getDriverId());
        assertEquals("Test", driverDTO.getPesel());
        assertEquals("Test", driverDTO.getPassword());
    }

    @Test
    void mapFromDTO() {
        DriverDTO test = DriverDTO.builder()
                .name("Test")
                .surname("Test")
                .driverId(1)
                .pesel("Test")
                .password("Test")
                .build();

        Driver driver = driverDTOMapper.mapFromDTO(test);
        assertEquals("Test", driver.getName());
        assertEquals("Test", driver.getSurname());
        assertEquals(1, driver.getDriverId());
        assertEquals("Test", driver.getPesel());
        assertEquals("Test", driver.getPassword());
    }

    @Test
    void testMappingToJsonAndBack() throws Exception {
        DriverDTO originalDTO = new DriverDTO();
        originalDTO.setName("Jan");
        originalDTO.setSurname("Kowalski");
        originalDTO.setPesel("51080948577");

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(originalDTO);

        DriverDTO deserializedDTO = objectMapper.readValue(json, DriverDTO.class);

        assertEquals(originalDTO.getName(), deserializedDTO.getName());
        assertEquals(originalDTO.getSurname(), deserializedDTO.getSurname());
        assertEquals(originalDTO.getPesel(), deserializedDTO.getPesel());
    }

}