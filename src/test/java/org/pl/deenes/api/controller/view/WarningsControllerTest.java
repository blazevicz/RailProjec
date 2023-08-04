package org.pl.deenes.api.controller.view;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.pl.deenes.api.controller.dto.RegionDTO;
import org.pl.deenes.api.controller.mapper.RegionDTOMapper;
import org.pl.deenes.infrastructure.repositories.RegionRepository;
import org.pl.deenes.model.Region;
import org.pl.deenes.services.WebScrapingRegionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = WarningsController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class WarningsControllerTest {
    @MockBean
    private final RegionRepository regionRepository;
    @MockBean
    private final RegionDTOMapper regionDTOMapper;
    @MockBean
    private final WebScrapingRegionsService webScrapingRegionsService;

    private MockMvc mockMvc;

    @Test
    void warningViewLoadProperty() throws Exception {
        Region region = Region.builder()
                .regionId(1)
                .actualWOS("asd")
                .actualWOSlink("asd.com")
                .zlkRegionNumber(5)
                .build();

        RegionDTO regionDTO = RegionDTO.builder()
                .actualWOS("asd")
                .actualWOSlink("asd.com")
                .zlkRegionNumber(5)
                .build();

        List<Region> allRegions = List.of(region);
        List<RegionDTO> allRegionDTOs = List.of(regionDTO);

        given(regionRepository.findAll()).willReturn(allRegions);
        given(regionDTOMapper.mapToDTO(region)).willReturn(regionDTO);

        mockMvc.perform(get("/warnings"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("warningsList"))
                .andExpect(model().attribute("warningsList", allRegionDTOs))
                .andExpect(view().name("warnings"));
    }
}
