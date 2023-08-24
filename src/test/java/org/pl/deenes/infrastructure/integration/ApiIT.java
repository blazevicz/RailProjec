package org.pl.deenes.infrastructure.integration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pl.deenes.infrastructure.mapper.NodeMapper;
import org.pl.deenes.infrastructure.repositories.LocalizationRepository;
import org.pl.deenes.osm.infrastructure.MapApi;
import org.pl.deenes.osm.model.Way;
import org.pl.deenes.services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
class ApiIT extends IntegrationReposIT {


    private final ApiService apiService;
    private final MapApi mapApi;
    private final NodeMapper nodeMapper;
    private final LocalizationRepository localizationRepository;

    @Test
    void shouldFindStationByNameAndReturnWayList() {

        List<Way> stationAndGetPosition =
                apiService.findStationAndGetPosition("BYTOM");

        Assertions.assertNotNull(stationAndGetPosition);

    }
}
