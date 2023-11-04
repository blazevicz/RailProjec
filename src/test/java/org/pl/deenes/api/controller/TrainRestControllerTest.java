package org.pl.deenes.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.pl.deenes.api.controller.exception.NotFound;
import org.pl.deenes.api.controller.mapper.TrainDTOMapper;
import org.pl.deenes.api.controller.rest.TrainRestController;
import org.pl.deenes.cfg.JwtService;
import org.pl.deenes.infrastructure.repositories.TokenRepository;
import org.pl.deenes.infrastructure.repositories.TrainRepository;
import org.pl.deenes.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TrainRestController.class)
@AutoConfigureMockMvc(addFilters = false)
class TrainRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TrainDTOMapper trainDTOMapper;

    @MockBean
    private TrainRepository trainRepository;

    @MockBean
    private JwtService jwtService;
    @MockBean
    private TokenRepository tokenRepository;


    @Test
    void shouldReturnTrainDetails() throws Exception {
        int trainKwr = 123;
        Train train = Train.builder().trainKwr(trainKwr).build();

        when(trainRepository.find(trainKwr)).thenReturn(Optional.of(train));

        mockMvc.perform(get("/api/train/{trainKwr}", trainKwr))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void deleteNotExistingTrainShouldThrowNowFoundEx() throws Exception {
        Integer trainKwrToDelete = 12345;

        doThrow(new NotFound("Train kwr: %s not found".formatted(trainKwrToDelete)))
                .when(trainRepository).delete(trainKwrToDelete);

        mockMvc.perform(delete("/api/train/delete/{trainKwr}", trainKwrToDelete))
                .andExpect(status().isNotFound());
    }
}