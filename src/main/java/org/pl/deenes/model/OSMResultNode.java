package org.pl.deenes.model;

import java.util.Map;


public record OSMResultNode(long id, double positionA, double positionB, Map<String, String> tags) {

}
