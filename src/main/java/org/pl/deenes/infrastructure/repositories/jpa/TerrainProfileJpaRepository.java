package org.pl.deenes.infrastructure.repositories.jpa;

import org.pl.deenes.infrastructure.entity.TerrainProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface TerrainProfileJpaRepository extends JpaRepository<TerrainProfileEntity, Integer> {

    LinkedList<TerrainProfileEntity> findAllByLineNumberIsAndKilometerBetween(Integer lineNumber, Double kilometerA, Double kilometerB);
}
