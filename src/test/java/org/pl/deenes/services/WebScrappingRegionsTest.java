package org.pl.deenes.services;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pl.deenes.features.WebScrappingRegions;
import org.pl.deenes.repositories.WebScapperRepo;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class WebScrappingRegionsTest {

    @Mock
    private WebScapperRepo webScapperRepo;

    @InjectMocks
    private WebScrappingRegions webScrappingRegions;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRegions() {
        String link1 = "https://www.lotoskolej.pl/region/1/zlk123";
        String link2 = "https://www.lotoskolej.pl/region/2/zlk456";
        Set<String> pageLinks = new HashSet<>();
        pageLinks.add(link1);
        pageLinks.add(link2);

        // używamy mocka webScrapingRegions.getPageLinks
        Mockito.when(WebScrappingRegions.getPageLinks(anyString())).thenReturn(pageLinks);

        Map<String, String> expected = new HashMap<>();
        expected.put("1", link1);
        expected.put("2", link2);

        Map<String, String> actual = WebScrappingRegions.getAllRegions();

        assertEquals(expected, actual);
    }

    @Test
    void testGetNewestWosFromRegion() {
        Set<String> pageLinks = new HashSet<>();
        pageLinks.add("https://www.lotoskolej.pl/repository/123");
        pageLinks.add("https://www.lotoskolej.pl/repository/456");
        String expected = "https://www.lotoskolej.pl/repository/456";
        String actual = WebScrappingRegions.getNewestWosFromRegion(pageLinks);
        assertEquals(expected, actual);
    }

    @Test
    void testGetNumberFromURL() {
        String url = "https://www.lotoskolej.pl/repository/123";
        int expected = 123;
        int actual = WebScrappingRegions.getNumberFromURL(url);
        assertEquals(expected, actual);
    }

    @Test
    void testGetPageLinks() throws IOException {
        String url = "https://www.lotoskolej.pl/2298/o_nas/wykaz_ostrzezen_stalych/";
        Document document = mock(Document.class);
        Elements elements = mock(Elements.class);
        when(document.select("a[href]")).thenReturn(elements);
        when(elements.attr("abs:href")).thenReturn("https://www.lotoskolej.pl/region/1/zlk123");

        Set<String> expected = new HashSet<>();
        expected.add("https://www.lotoskolej.pl/region/1/zlk123");
        Set<String> actual = WebScrappingRegions.getPageLinks(url);

        assertEquals(expected, actual);
    }

}