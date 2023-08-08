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
    private static final HashSet<String> links = new HashSet<>();
    private static final Map<String, String> result = new HashMap<>();


    private final RegionRepository regionRepository;

    public static @NonNull String getNewestWosFromRegion(@NonNull Set<String> pageLinks) {
        Integer findBiggestLinkValue = pageLinks.stream()
                .filter(a -> a.contains("repository"))
                .map(WebScrapingRegionsService::getNumberFromURL)
                .max(Comparator.naturalOrder())
                .orElseGet(() -> 333);

        return REPO_LINK.concat(findBiggestLinkValue.toString());
    }

    public static @NonNull Map<String, String> getAllRegions() {
        links.clear();
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

    public static @NonNull Integer getNumberFromURL(@NonNull String url) {
        String replace = url.replace(REPO_LINK, "").replace("/", "");
        return Integer.valueOf(replace);
    }

    public static @NonNull Set<String> getPageLinks(String url) {
        Set<String> linksMy = new HashSet<>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements linksOnPage = document.select("a[href]");

            for (Element page : linksOnPage) {
                String link = page.attr("abs:href");
                if (link.contains(DOMAIN) && (!links.contains(link))) {
                    linksMy.add(link);
                    links.add(link);
                }
            }
        } catch (IOException e) {
            log.error(url + " " + e.getMessage());
        }
        return linksMy;
    }

    private static Region getRegion(Map.@NonNull Entry<String, String> stringStringEntry) {
        return Region.builder()
                .zlkRegionNumber(Integer.valueOf(stringStringEntry.getKey()))
                .actualWOS(String.valueOf(getNumberFromURL(stringStringEntry.getValue())))
                .actualWOSlink(stringStringEntry.getValue())
                .build();
    }

    @Transactional
    public void runner() {
        Map<String, String> mapWithAllRegionsLinks = getAllRegions();

        for (var region : mapWithAllRegionsLinks.entrySet()) {

            Set<String> pageLinks1 = getPageLinks(region.getValue());
            String newestWosFromRegion1 = getNewestWosFromRegion(pageLinks1);
            result.put(region.getKey(), newestWosFromRegion1);
        }

        for (Map.Entry<String, String> stringStringEntry : result.entrySet()) {

            var byZlkRegionNumber = regionRepository.findByZlkRegionNumber(Integer.valueOf(stringStringEntry.getKey()));
            if (byZlkRegionNumber.isEmpty()) {
                Region build = getRegion(stringStringEntry);
                regionRepository.save(build);

            } else {
                byZlkRegionNumber.get().setActualWOS(String.valueOf(getNumberFromURL(stringStringEntry.getValue())));
                byZlkRegionNumber.get().setActualWOSlink(stringStringEntry.getValue());
                regionRepository.save(byZlkRegionNumber.get());
            }
        }
    }
}