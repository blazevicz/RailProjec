package org.pl.deenes.features;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pl.deenes.repositories.WebScapperRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class WebScrappingRegions {
    private static final String ALL_REGIONS_LINK = "https://www.lotoskolej.pl/2298/o_nas/wykaz_ostrzezen_stalych/";
    private static final String REPO_LINK = "https://www.lotoskolej.pl/repository/";
    private static final String DOMAIN = "https://www.lotoskolej.pl/";
    private static final HashSet<String> links = new HashSet<>();

    private WebScapperRepo webScapperRepo;

    private static @NonNull String getNewestWosFromRegion(@NonNull Set<String> pageLinks) {
        Integer findBiggestLinkValue = pageLinks.stream()
                .filter(a -> a.contains("repository"))
                .map(WebScrappingRegions::getNumberFromURL)
                .max(Comparator.naturalOrder())
                .orElseGet(() -> 333);

        return REPO_LINK.concat(findBiggestLinkValue.toString());
    }

    private static @NonNull Map<String, String> getAllRegions() {
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

    private static @NonNull Integer getNumberFromURL(@NonNull String url) {
        String replace = url.replace(REPO_LINK, "").replace("/", "");
        return Integer.valueOf(replace);
    }

    private static @NonNull Set<String> getPageLinks(String url) {
        Set<String> linksMy = new HashSet<>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements linksOnPage = document.select("a[href]");

            for (Element page : linksOnPage) {
                String link = page.attr("abs:href");
                if (link.contains(DOMAIN)) {
                    if (!links.contains(link)) {
                        linksMy.add(link);
                        links.add(link);
                    }
                }
            }
        } catch (IOException e) {
            log.error(url + " " + e.getMessage());
        }
        return linksMy;
    }

    @Transactional
    public void runner() {
        Map<String, String> result = new HashMap<>();
        Map<String, String> mapWithAllRegionsLinks = getAllRegions();

        for (var region : mapWithAllRegionsLinks.entrySet()) {
            Set<String> pageLinks1 = getPageLinks(region.getValue());
            String newestWosFromRegion1 = getNewestWosFromRegion(pageLinks1);
            result.put(region.getKey(), newestWosFromRegion1);
        }
        
        for (Map.Entry<String, String> stringStringEntry : result.entrySet()) {
            webScapperRepo.updateByZlkRegionNumber(
                    Integer.valueOf(stringStringEntry.getKey()),
                    String.valueOf(getNumberFromURL(stringStringEntry.getValue())),
                    stringStringEntry.getValue()
            );
        }
    }
}