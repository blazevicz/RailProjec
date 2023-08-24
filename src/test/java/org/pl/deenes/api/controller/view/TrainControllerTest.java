package org.pl.deenes.api.controller.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.pl.deenes.api.controller.dto.TrainDTO;
import org.pl.deenes.api.controller.mapper.DriverDTOMapper;
import org.pl.deenes.api.controller.mapper.TrainDTOMapper;
import org.pl.deenes.infrastructure.repositories.DriverRepository;
import org.pl.deenes.infrastructure.repositories.TrainRepository;
import org.pl.deenes.model.Driver;
import org.pl.deenes.model.Train;
import org.pl.deenes.services.ResultServiceImpl;
import org.pl.deenes.services.interfaces.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
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

    @MockBean
    private TrainService trainService;


    @Test
    void shouldReturnTrainsPageWithExistingTrainsAndDrivers() throws Exception {
        List<Train> trains = Arrays.asList(
                Train.builder().trainId(1).trainKwr(123).build(),
                Train.builder().trainId(2).trainKwr(223).build()
        );
        when(trainRepository.findAll()).thenReturn(trains);

        List<Driver> drivers = Arrays.asList(
                Driver.builder().driverId(1).name("A").pesel("66071749125L").build(),
                Driver.builder().driverId(2).name("B").pesel("61071749125L").build());
        when(driverRepository.findAllDrivers()).thenReturn(drivers);

        mockMvc.perform(get("/trains"))
                .andExpect(status().isOk())
                .andExpect(view().name("train"))
                .andExpect(model().attributeExists("existingTrains"))
                .andExpect(model().attributeExists("existingDrivers"));
    }

    @Test
    void shouldReturnTrainDetailsPageForExistingTrain() throws Exception {
        int trainKwr = 1;
        Train train = Train.builder()
                .trainId(1)
                .trainKwr(trainKwr)
                .trainNumber(1)
                .trainMaxLength(1)
                .trainMaxSpeed(1)
                .trainMaxWeight(1)
                .build();
        when(trainRepository.find(trainKwr)).thenReturn(Optional.of(train));

        TrainDTO trainDTO = trainMapper.mapToDTO(train);

        mockMvc.perform(get("/trains/{trainKwr}", trainKwr))
                .andExpect(status().isOk())
                .andExpect(view().name("trainDetails"))
                .andExpect(model().attribute("train", trainDTO));
    }


    @Test
    void shouldReturnTrainsPageWithEmptyListWhenNoTrains() throws Exception {
        when(trainRepository.findAll()).thenReturn(Collections.emptyList());
        when(driverRepository.findAllDrivers()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/trains"))
                .andExpect(status().isOk())
                .andExpect(view().name("train"))
                .andExpect(model().attributeExists("existingTrains", "existingDrivers", "pdfFiles"))
                .andExpect(model().attribute("existingTrains", List.of()))
                .andExpect(model().attribute("existingDrivers", List.of()))
                .andExpect(model().attribute("pdfFiles", List.of(
                        "RJ_SKRJ_666401_464028_9.pdf",
                        "RJ_SKRJ_956925_644010_1.pdf"
                )));
    }
}