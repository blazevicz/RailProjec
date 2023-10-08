package org.pl.deenes.api.controller.rest;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.pl.deenes.api.controller.dto.TrainDTO;
import org.pl.deenes.api.controller.mapper.TrainDTOMapper;
import org.pl.deenes.infrastructure.repositories.TrainRepository;
import org.pl.deenes.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TrainRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class TrainRestControllerTest {

    @MockBean
    private TrainDTOMapper trainDTOMapper;
    @MockBean
    private TrainRepository trainRepository;

    private MockMvc mockMvc;

    @Test
    void testTrainInfo() throws Exception {
        int trainKwr = 123;
        Train train = Train.builder().trainKwr(trainKwr).build();

        TrainDTO trainDTO = TrainDTO.builder().trainKwr(trainKwr).build();

        when(trainRepository.find(trainKwr)).thenReturn(Optional.of(train));
        when(trainDTOMapper.mapToDTO(train)).thenReturn(trainDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/train/{trainKwr}", trainKwr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].trainKwr").value(trainKwr));
    }

    @Test
    void trainInfo_NonExistingTrain_ShouldReturnNotFound() throws Exception {
        int trainKwr = 123;

        when(trainRepository.find(trainKwr)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/train/{trainKwr}", trainKwr))
                .andExpect(status().isNotFound());
    }

}
