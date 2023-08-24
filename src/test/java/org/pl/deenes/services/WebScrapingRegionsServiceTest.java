package org.pl.deenes.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pl.deenes.infrastructure.repositories.RegionRepository;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class WebScrapingRegionsServiceTest {

    @Mock
    private RegionRepository regionRepository;

    @InjectMocks
    private WebScrapingRegionsService webScrapingRegionsService;

    @Test
    void GetNewestWosFromRegion() {
        Set<String> pageLinks = new HashSet<>();
        pageLinks.add("https://www.lotoskolej.pl/repository/100");
        pageLinks.add("https://www.lotoskolej.pl/repository/200");
        pageLinks.add("https://www.lotoskolej.pl/repository/300");

        String result = webScrapingRegionsService.getNewestWosFromRegion(pageLinks);

        assertThat(result).isEqualTo("https://www.lotoskolej.pl/repository/300");
    }

    @Test
    void NewestWosDontFindNewest() {
        Set<String> pageLinks = new HashSet<>();
        Assertions.assertThrows(Exception.class, () -> webScrapingRegionsService.getNewestWosFromRegion(pageLinks));
    }

    @Test
    void GetNumberFromURL() {
        String url = "https://www.lotoskolej.pl/repository/100";

        Integer result = webScrapingRegionsService.getNumberFromURL(url);

        assertThat(result).isEqualTo(100);
    }
}