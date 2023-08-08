package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import org.pl.deenes.osm.infrastructure.MapApi;
import org.pl.deenes.osm.model.GetMapDataByBoundingBox200Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ApiService {

    private final MapApi mapApi;

    public void test() {

        Mono<GetMapDataByBoundingBox200Response> mapDataByBoundingBox =
                mapApi.getMapDataByBoundingBox("13.377639198,52.5162399276,13.3778497142,52.5163460219");

        mapDataByBoundingBox.subscribe(
                response -> {
                    System.out.println(response);
                },
                error -> {
                    System.out.println(error);
                }
        );

    }
}
