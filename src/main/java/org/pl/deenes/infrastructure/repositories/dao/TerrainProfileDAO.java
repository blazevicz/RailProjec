package org.pl.deenes.infrastructure.repositories.dao;

import org.pl.deenes.model.TerrainProfile;

import java.util.LinkedList;

public interface TerrainProfileDAO {
    LinkedList<TerrainProfile> findAllByLineNumberIsAndKilometerBetween(Integer lineNumber, Double kilometerA, Double kilometerB);

}
