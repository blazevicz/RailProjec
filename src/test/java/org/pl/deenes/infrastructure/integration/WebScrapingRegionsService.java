package org.pl.deenes.infrastructure.integration;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.pl.deenes.infrastructure.repositories.RegionRepository;
import org.pl.deenes.model.Region;
import org.pl.deenes.services.WebScrapingRegionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@AllArgsConstructor(onConstructor = @__(@Autowired))
class WebScrapingRegionsServiceIT extends IntegrationReposIT {

    private WebScrapingRegionsService webScrapingRegionsService;

    @MockBean
    private RegionRepository regionRepository;

    @Test
    void testRunner() {
        when(regionRepository.findByZlkRegionNumber(anyInt())).thenReturn(Optional.of(Region.builder().build()));
        when(regionRepository.save(any(Region.class))).thenReturn(Region.builder().build());

        webScrapingRegionsService.runner();

        verify(regionRepository, times(8)).findByZlkRegionNumber(anyInt());
        verify(regionRepository, times(8)).save(any(Region.class));
    }

}
