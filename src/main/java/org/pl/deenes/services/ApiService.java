package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.infrastructure.mapper.NodeMapper;
import org.pl.deenes.infrastructure.repositories.LocalizationRepository;
import org.pl.deenes.model.Localization;
import org.pl.deenes.osm.infrastructure.MapApi;
import org.pl.deenes.osm.model.GetMapDataByBoundingBox200Response;
import org.pl.deenes.osm.model.GetMapDataByBoundingBox200ResponseElementsInner;
import org.pl.deenes.osm.model.Way;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Slf4j
public class ApiService {

    private final MapApi mapApi;
    private final NodeMapper nodeMapper;
    private final LocalizationRepository localizationRepository;

    @NonNull
    private static List<Way> filterStationBuildingsAndRailLines(@NonNull List<Way> locationByBbox) {
        return locationByBbox.stream().filter(
                        map -> {
                            assert map.getTags() != null;
                            return map.getTags().containsKey("ref")
                                    && map.getTags().containsKey("railway")
                                    || map.getTags().containsKey("building")
                                    && map.getTags().get("building").equals("train_station");
                        })
                .toList();
    }

    public List<Way> findStationAndGetPosition(String stationName) {
        Localization localization;
        try {
            localization = localizationRepository.findByStation(stationName).orElseThrow();
            String bbox = convertLocalizationToBbox(localization.latitude(), localization.longitude());
            List<Way> locationByBbox = findLocationByBbox(bbox);
            return filterStationBuildingsAndRailLines(locationByBbox);
        } catch (NoSuchElementException nf) {
            log.warn(nf + " " + stationName);
        }
        return Collections.emptyList();
    }

    public List<Way> findLocationByBbox(String bbox) {

        List<Way> nodeList = new ArrayList<>();

        Mono<GetMapDataByBoundingBox200Response> mapDataByBoundingBox =
                mapApi.getMapDataByBoundingBox(bbox);

        List<GetMapDataByBoundingBox200ResponseElementsInner> elements = mapDataByBoundingBox.map(GetMapDataByBoundingBox200Response::getElements).block();
        assert elements != null;
        for (GetMapDataByBoundingBox200ResponseElementsInner element : elements) {
            if (element.getType().equals(GetMapDataByBoundingBox200ResponseElementsInner.TypeEnum.WAY)) {
                Way way = nodeMapper.mapFromEntityToWay(element);
                nodeList.add(way);
            }
        }
        return nodeList;
    }

    private @NonNull String convertLocalizationToBbox(Double latitude, Double longitude) {
        double bboxAcorner = latitude * 0.99999;
        double bboxBcorner = longitude * 0.99999;
        double bboxCcorner = latitude * 1.00001;
        double bboxDcorner = longitude * 1.00001;

        return bboxBcorner +
                "," +
                bboxAcorner +
                "," +
                bboxDcorner +
                "," +
                bboxCcorner;
    }
}
