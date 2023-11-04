package org.pl.deenes.api.controller.view;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.pl.deenes.api.controller.dto.TerrainProfileDTO;
import org.pl.deenes.api.controller.mapper.TerrainProfileDTOMapper;
import org.pl.deenes.cfg.JwtService;
import org.pl.deenes.infrastructure.repositories.TokenRepository;
import org.pl.deenes.infrastructure.repositories.TrainRepository;
import org.pl.deenes.model.TerrainProfile;
import org.pl.deenes.model.Train;
import org.pl.deenes.model.TrainStats;
import org.pl.deenes.services.ApiService;
import org.pl.deenes.services.TerrainProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = MapAndProfileController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class MapAndProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TerrainProfileServiceImpl terrainProfileService;

    @MockBean
    private TerrainProfileDTOMapper terrainProfileDTOMapper;

    @MockBean
    private TrainRepository trainRepository;

    @MockBean
    private ApiService apiService;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private TokenRepository tokenRepository;

    @Test
    void testTrainDetails() throws Exception {
        int trainKwr = 12345;
        Train train = Train.builder().trainKwr(trainKwr).build();
        train.setTrainStats(new HashSet<>());

        TrainStats trainStats =
                TrainStats.builder().lineNumber(1).firstKilometer(0.0).lastKilometer(10.1).build();

        train.getTrainStats().add(trainStats);

        TerrainProfile terrainProfile = TerrainProfile.builder().build();
        List<TerrainProfile> terrainProfiles = Collections.singletonList(terrainProfile);

        when(trainRepository.find(trainKwr)).thenReturn(Optional.of(train));
        when(terrainProfileService.calculateSlope(1, 0.0, 10.1)).thenReturn(terrainProfiles);
        when(terrainProfileDTOMapper.mapToDTO(any(TerrainProfile.class))).thenReturn(new TerrainProfileDTO());

        mockMvc.perform(MockMvcRequestBuilders.get("/trains/{trainKwr}/profile", trainKwr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("terrainProfiles"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("listWithStationInfo"))
                .andExpect(MockMvcResultMatchers.view().name("map_profile"));
    }
}
