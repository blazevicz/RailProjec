package org.pl.deenes.api.controller.rest;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.pl.deenes.api.controller.dto.TrainDTO;
import org.pl.deenes.api.controller.mapper.TrainDTOMapper;
import org.pl.deenes.infrastructure.repositories.TrainRepository;
import org.pl.deenes.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TrainRestController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class TrainRestControllerTest {

    @MockBean
    private TrainDTOMapper trainDTOMapper;

    @MockBean
    private TrainRepository trainRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void trainInfo_ExistingTrain_ShouldReturnTrainDTO() throws Exception {
        int trainKwr = 123;
        Train train = Train.builder().trainKwr(trainKwr).build();
        TrainDTO trainDTO = TrainDTO.builder().trainKwr(trainKwr).build();

        when(trainRepository.find(trainKwr)).thenReturn(List.of(train));
        when(trainDTOMapper.mapToDTO(train)).thenReturn(trainDTO);

        mockMvc.perform(get("/api/train/{trainKwr}", trainKwr))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.trainKwr").value(trainKwr));
    }

    @Test
    void trainInfo_NonExistingTrain_ShouldReturnNotFound() throws Exception {
        int trainKwr = 123;

        when(trainRepository.find(trainKwr)).thenReturn(List.of());

        mockMvc.perform(get("/api/train/{trainKwr}", trainKwr))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteTrain_ExistingTrain_ShouldDeleteTrain() throws Exception {
        int trainKwr = 123;

        mockMvc.perform(delete("/api/train/delete/{trainKwr}", trainKwr))
                .andExpect(status().isOk());

        // Add any additional assertions if needed
    }
}
