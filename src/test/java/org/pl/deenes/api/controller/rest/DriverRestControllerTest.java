package org.pl.deenes.api.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.pl.deenes.api.controller.dto.DriverDTO;
import org.pl.deenes.api.controller.mapper.DriverDTOMapper;
import org.pl.deenes.infrastructure.repositories.DriverRepository;
import org.pl.deenes.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = DriverRestController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@AutoConfigureMockMvc(addFilters = false)
class DriverRestControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockBean
    private DriverDTOMapper driverDTOMapper;

    @MockBean
    private DriverRepository driverRepository;

    @Test
    void allDrivers() throws Exception {
        List<Driver> drivers = List.of(
                Driver.builder().driverId(1).name("Jan").surname("Trzewiczek").build(),
                Driver.builder().driverId(2).name("Edyta").surname("Fafik").build()
        );

        List<DriverDTO> driverDTOs = List.of(
                DriverDTO.builder().driverId(1).name("Jan").surname("Trzewiczek").build(),
                DriverDTO.builder().driverId(2).name("Edyta").surname("Fafik").build()
        );

        when(driverRepository.findAllDrivers()).thenReturn(drivers);
        when(driverDTOMapper.mapToDTO(any())).thenReturn(driverDTOs.get(0), driverDTOs.get(1));

        mockMvc.perform(get("/api/driver/all"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Jan"))
                .andExpect(jsonPath("$[0].surname").value("Trzewiczek"))
                .andExpect(jsonPath("$[1].name").value("Edyta"))
                .andExpect(jsonPath("$[1].surname").value("Fafik"));
    }

    @Test
    void findDriver() throws Exception {
        int driverId = 1;
        Driver driver = Driver.builder().driverId(driverId).name("Jan").surname("Trzewiczek").build();
        DriverDTO driverDTO = DriverDTO.builder().driverId(driverId).name("Jan").surname("Trzewiczek").build();

        when(driverRepository.findDriverById(driverId)).thenReturn(Optional.of(driver));
        when(driverDTOMapper.mapToDTO(driver)).thenReturn(driverDTO);

        mockMvc.perform(get("/api/driver/{driverId}", driverId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jan"))
                .andExpect(jsonPath("$.surname").value("Trzewiczek"));
    }


    @Test
    void addDriver() throws Exception {
        DriverDTO requestDto = DriverDTO.builder().name("Jan").surname("Trzewiczek").pesel("69070611222").build();
        Driver savedDriver = Driver.builder().driverId(1).name("Jan").surname("Trzewiczek").pesel("69070611222").build();

        when(driverDTOMapper.mapFromDTO(requestDto)).thenReturn(savedDriver);
        when(driverRepository.save(any())).thenReturn(savedDriver);
        when(driverDTOMapper.mapToDTO(savedDriver)).thenReturn(requestDto);

        mockMvc.perform(post("/api/driver/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Jan\",\"surname\":\"Trzewiczek\", \"pesel\": \"69070611222\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Jan"))
                .andExpect(jsonPath("$.surname").value("Trzewiczek"))
                .andExpect(jsonPath("$.pesel").value("69070611222"));
    }

    @Test
    void deleteDriver() throws Exception {
        int driverId = 1;
        mockMvc.perform(delete("/api/driver/{driverId}/delete", driverId))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void addDriverInvalidPeselShouldReturnBadRequest() throws Exception {

        DriverDTO driverDTO =
                DriverDTO.builder().driverId(null)
                        .name("Jan")
                        .surname("Kowalski")
                        .pesel("11")
                        .build();

        when(driverDTOMapper.mapFromDTO(any(DriverDTO.class))).thenReturn(Driver.builder().build());
        when(driverRepository.save(any(Driver.class))).thenReturn(Driver.builder().build());
        when(driverDTOMapper.mapToDTO(any(Driver.class))).thenReturn(driverDTO);

        mockMvc.perform(post("/api/driver/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(driverDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addDriverValidPeselShouldReturnOk() throws Exception {

        DriverDTO requestDto = DriverDTO.builder()
                .name("Jan")
                .surname("Trzewiczek")
                .pesel("69070611222")
                .build();

        when(driverRepository.save(any(Driver.class))).thenReturn(Driver.builder().build());

        mockMvc.perform(post("/api/driver/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());
    }
}
