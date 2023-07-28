package org.pl.deenes.services;

import de.westnordost.osmapi.OsmConnection;
import de.westnordost.osmapi.overpass.OverpassMapDataApi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.model.OSMResultNode;
import org.pl.deenes.services.interfaces.OsmApiService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class OsmApiServiceImpl implements OsmApiService {
    public static final String BBOX =
            "[bbox:50.27269961133207,19.186969757080075,50.35511162860444,19.313243865966793]";
    public static final String QUERY =
            "; nwr[railway=yard]; out meta;";

    public List<OSMResultNode> findNodes(String bbox, String query) {
        OsmConnection connection = new OsmConnection("https://overpass-api.de/api/", null);
        OverpassMapDataApi overpass = new OverpassMapDataApi(connection);
        overpass.queryElements(bbox + query, new OverpassResponseHandler());
        return OverpassResponseHandler.list;

    }


}
