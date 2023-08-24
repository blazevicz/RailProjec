package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pl.deenes.infrastructure.repositories.RegionRepository;
import org.pl.deenes.model.Region;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class WebScrapingRegionsService {

    private static final String ALL_REGIONS_LINK = "https://www.lotoskolej.pl/2298/o_nas/wykaz_ostrzezen_stalych/";
    private static final String REPO_LINK = "https://www.lotoskolej.pl/repository/";
    private static final String DOMAIN = "https://www.lotoskolej.pl/";

    private final RegionRepository regionRepository;

    @Transactional
    public void runner() {
        Map<String, String> mapWithAllRegionsLinks = getAllRegions();
        Map<String, String> result = new HashMap<>();

        for (var region : mapWithAllRegionsLinks.entrySet()) {
            Set<String> pageLinks = getPageLinks(region.getValue());
            String newestWosFromRegion = getNewestWosFromRegion(pageLinks);
            result.put(region.getKey(), newestWosFromRegion);
        }

        for (Map.Entry<String, String> entry : result.entrySet()) {
            Optional<Region> optionalRegion = regionRepository.findByZlkRegionNumber(Integer.valueOf(entry.getKey()));

            Region regionToUpdate = optionalRegion.orElseGet(() -> createRegion(entry.getKey(), entry.getValue()));

            regionToUpdate.setActualWOS(String.valueOf(getNumberFromURL(entry.getValue())));
            regionToUpdate.setActualWOSlink(entry.getValue());
            regionRepository.save(regionToUpdate);
        }
    }

    String getNewestWosFromRegion(@NonNull Set<String> pageLinks) {
        Integer findBiggestLinkValue = pageLinks.stream()
                .filter(a -> a.contains("repository"))
                .map(this::getNumberFromURL)
                .max(Comparator.naturalOrder())
                .orElseThrow();

        return REPO_LINK.concat(findBiggestLinkValue.toString());
    }

    Map<String, String> getAllRegions() {
        Set<String> links = getPageLinks(ALL_REGIONS_LINK);
        Map<String, String> regionsMap = new HashMap<>();
        for (String link : links) {
            if (link.contains("zlk")) {
                String key = link.substring(link.lastIndexOf('/') + 2, link.lastIndexOf("zlk") - 1);
                if (!regionsMap.containsKey(key)) {
                    regionsMap.put(key, link);
                } else {
                    log.warn("Link {} already exists in the map under key {}. Skipping...", link, key);
                }
            }
        }
        return regionsMap;
    }

    public Integer getNumberFromURL(@NonNull String url) {
        String replace = url.replace(REPO_LINK, "").replace("/", "");
        return Integer.valueOf(replace);
    }

    public Set<String> getPageLinks(String url) {
        Set<String> linksMy = new HashSet<>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements linksOnPage = document.select("a[href]");

            for (Element page : linksOnPage) {
                String link = page.attr("abs:href");
                if (link.contains(DOMAIN)) {
                    linksMy.add(link);
                }
            }
        } catch (IOException e) {
            log.error(url + " " + e.getMessage());
        }
        return linksMy;
    }

    private Region createRegion(String key, String value) {
        return Region.builder()
                .zlkRegionNumber(Integer.valueOf(key))
                .actualWOS(String.valueOf(getNumberFromURL(value)))
                .actualWOSlink(value)
                .build();
    }
}
