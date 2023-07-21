package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.osm.infrastructure.MapApi;
import org.pl.deenes.osm.model.GetMapDataByBoundingBox200Response;
import org.pl.deenes.services.interfaces.OsmApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class OsmApiServiceImpl implements OsmApiService {

    private final MapApi mapApi;

    public void checkApi() {

        Mono<ResponseEntity<GetMapDataByBoundingBox200Response>> mapDataByBoundingBoxWithHttpInfo = mapApi.getMapDataByBoundingBoxWithHttpInfo("13.377639198,52.5162399276,13.3778497142,52.5163460219");
        mapDataByBoundingBoxWithHttpInfo.subscribe(
                responseEntity -> {
                    System.out.println(responseEntity.getBody());
                    log.warn(responseEntity.getStatusCode().toString());
                }

        );
    }
}
