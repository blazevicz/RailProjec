package org.pl.deenes.infrastructure.repositories;

import lombok.AllArgsConstructor;
import org.pl.deenes.infrastructure.mapper.TerrainProfileMapper;
import org.pl.deenes.infrastructure.repositories.dao.TerrainProfileDAO;
import org.pl.deenes.infrastructure.repositories.jpa.TerrainProfileJpaRepository;
import org.pl.deenes.model.TerrainProfile;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class TerrainProfileRepository implements TerrainProfileDAO {

    private final TerrainProfileJpaRepository terrainProfileJpaRepository;
    private final TerrainProfileMapper terrainProfileMapper;


    @Override
    public LinkedList<TerrainProfile> findAllByLineNumberIsAndKilometerBetween(Integer lineNumber, Double kilometerA, Double kilometerB) {
        return terrainProfileJpaRepository.findAllByLineNumberIsAndKilometerBetween(lineNumber, kilometerA, kilometerB)
                .stream()
                .map(terrainProfileMapper::mapFromEntity).collect(Collectors.toCollection(LinkedList::new));
    }
}
