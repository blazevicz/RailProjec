package org.pl.deenes.services;

import de.westnordost.osmapi.map.data.BoundingBox;
import de.westnordost.osmapi.map.data.Node;
import de.westnordost.osmapi.map.data.Relation;
import de.westnordost.osmapi.map.data.Way;
import de.westnordost.osmapi.map.handler.MapDataHandler;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.api.controller.exception.OperationNotSupported;
import org.pl.deenes.model.OSMResultNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class OverpassResponseHandler implements MapDataHandler {

    protected static final List<OSMResultNode> list = new ArrayList<>();

    @Override
    public void handle(Node node) {
        log.warn(String.valueOf(node.getId()));
        if (node.getTags().containsKey("railway")) {

            Map<String, String> readableTags = new HashMap<>(node.getTags());

            list.add(new OSMResultNode(
                    node.getId(),
                    node.getPosition().getLatitude(),
                    node.getPosition().getLongitude(),
                    readableTags));
        }
    }


    @Override
    public void handle(BoundingBox bounds) {
        // throw new OperationNotSupported("Bounds is not supported");

    }


    @Override
    public void handle(Way way) {
        throw new OperationNotSupported("Way is not supported");
    }

    @Override
    public void handle(Relation relation) {
        throw new OperationNotSupported("Relation is not supported");

    }
}
