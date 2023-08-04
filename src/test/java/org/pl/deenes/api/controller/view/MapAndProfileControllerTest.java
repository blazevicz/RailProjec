package org.pl.deenes.api.controller.view;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.pl.deenes.api.controller.dto.TerrainProfileDTO;
import org.pl.deenes.api.controller.mapper.TerrainProfileDTOMapper;
import org.pl.deenes.infrastructure.repositories.AnalyseRepository;
import org.pl.deenes.infrastructure.repositories.TrainStatsRepository;
import org.pl.deenes.model.Analyse;
import org.pl.deenes.model.TerrainProfile;
import org.pl.deenes.model.TrainStats;
import org.pl.deenes.services.TerrainProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = MapAndProfileController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class MapAndProfileControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private TerrainProfileServiceImpl terrainProfileService;

    @MockBean
    private AnalyseRepository analyseRepository;

    @MockBean
    private TerrainProfileDTOMapper terrainProfileDTOMapper;

    @MockBean
    private TrainStatsRepository trainStatsRepository;

    @Test
    void testTrainDetails() throws Exception {
        int trainKwr = 12345;

        Analyse analyse1 = Analyse.builder()
                .trainKwr(123)
                .trainAnalyseId(1)
                .build();

        Analyse analyse2 = Analyse.builder()
                .trainKwr(123)
                .trainAnalyseId(2)
                .build();

        TrainStats trainStats1 = TrainStats.builder()
                .trainStatsId(1)
                .build();
        TrainStats trainStats2 = TrainStats.builder()
                .trainStatsId(2)
                .build();


        List<Analyse> allByTrainKwr = List.of(analyse1, analyse2);

        List<TerrainProfile> terrainProfiles1 = Collections.singletonList(TerrainProfile.builder().build());
        List<TerrainProfile> terrainProfiles2 = Collections.singletonList(TerrainProfile.builder().build());

        when(analyseRepository.findAllByTrainKwr(trainKwr)).thenReturn(allByTrainKwr);
        when(trainStatsRepository.findAllByTrainStatsId(1)).thenReturn(Optional.of(trainStats1));
        when(trainStatsRepository.findAllByTrainStatsId(2)).thenReturn(Optional.of(trainStats2));
        when(terrainProfileService.calculateSlope(1, 0.0, 10.1)).thenReturn(terrainProfiles1);
        when(terrainProfileService.calculateSlope(2, 11.2, 20.1)).thenReturn(terrainProfiles2);
        when(terrainProfileDTOMapper.mapToDTO(any(TerrainProfile.class))).thenReturn(new TerrainProfileDTO());
        mockMvc.perform(MockMvcRequestBuilders.get("/trains/{trainKwr}/profile", trainKwr))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("terrainProfiles"))
                .andExpect(view().name("map_profile"));
    }
}