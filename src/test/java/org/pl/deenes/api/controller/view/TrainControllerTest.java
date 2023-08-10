package org.pl.deenes.api.controller.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.pl.deenes.api.controller.mapper.DriverDTOMapper;
import org.pl.deenes.api.controller.mapper.TrainDTOMapper;
import org.pl.deenes.infrastructure.repositories.DriverRepository;
import org.pl.deenes.infrastructure.repositories.TrainRepository;
import org.pl.deenes.model.Driver;
import org.pl.deenes.model.Train;
import org.pl.deenes.services.ResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TrainController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class TrainControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockBean
    private TrainRepository trainRepository;

    @MockBean
    private DriverRepository driverRepository;

    @MockBean
    private TrainDTOMapper trainMapper;

    @MockBean
    private DriverDTOMapper driverMapper;

    @MockBean
    private ResultServiceImpl resultService;

    @Test
    void testGetTrainsPage() throws Exception {
        List<Train> trains = Arrays.asList(
                Train.builder().trainId(1).trainKwr(123).build(),
                Train.builder().trainId(2).trainKwr(223).build()
        );
        when(trainRepository.findAll()).thenReturn(trains);

        List<Driver> drivers = Arrays.asList(
                Driver.builder().driverId(1).name("A").pesel(123123123).build(),
                Driver.builder().driverId(2).name("B").pesel(32513).build());
        when(driverRepository.findAllDrivers()).thenReturn(drivers);

        mockMvc.perform(get("/trains"))
                .andExpect(status().isOk())
                .andExpect(view().name("train"))
                .andExpect(model().attributeExists("existingTrains"))
                .andExpect(model().attributeExists("existingDrivers"));
    }

    @Test
    void testGetTrainDetails() throws Exception {
        int trainKwr = 1;
        Train train = Train.builder().trainId(1).trainKwr(123).build();
        when(trainRepository.find(trainKwr)).thenReturn(Optional.of(train));
        mockMvc.perform(get("/trains/{trainKwr}", trainKwr))
                .andExpect(status().isOk())
                .andExpect(view().name("trainDetails"));
    }

    @Test
    void testGetTrainDetails_NotFound() throws Exception {
        int trainId = 1;
        when(trainRepository.find(trainId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/trains/{trainId}", trainId))
                .andExpect(status().is5xxServerError())
                .andExpect(view().name("error"));
    }
}