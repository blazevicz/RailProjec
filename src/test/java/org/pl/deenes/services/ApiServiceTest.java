package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.pl.deenes.infrastructure.mapper.NodeMapper;
import org.pl.deenes.infrastructure.repositories.LocalizationRepository;
import org.pl.deenes.model.Localization;
import org.pl.deenes.osm.infrastructure.MapApi;
import org.pl.deenes.osm.model.Way;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
class ApiServiceTest {

    private final LocalizationRepository localizationRepository;
    private final ApiService apiService;

    @MockBean
    private final MapApi mapApi;
    @MockBean
    private final NodeMapper nodeMapper;


    @Test
    @Disabled
    void shouldFindStationByName() {
        String stationName = "BYTOM";

        Localization byStation = localizationRepository.findByStation(stationName);

        Assertions.assertEquals(stationName, byStation.station());

    }

    @Test
    @Disabled
    void test() {
        String stationName = "BYTOM";

        List<Way> stationAndGetPosition = apiService.findStationAndGetPosition(stationName);


    }

}