package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import org.pl.deenes.infrastructure.repositories.TerrainProfileRepository;
import org.pl.deenes.model.TerrainProfile;
import org.pl.deenes.services.interfaces.TerrainProfileService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class TerrainProfileServiceImpl implements TerrainProfileService {
    private final TerrainProfileRepository terrainProfileJpaRepository;


    public List<TerrainProfile> calculateSlope(Integer lineNumber, Double kilometerA, Double kilometerB) {

        LinkedList<TerrainProfile> allByLineNumberIsAndKilometerBetween = terrainProfileJpaRepository.findAllByLineNumberIsAndKilometerBetween(lineNumber, kilometerA, kilometerB);
        for (int i = 0; i < allByLineNumberIsAndKilometerBetween.size() - 1; i++) {
            TerrainProfile terrainProfileA = allByLineNumberIsAndKilometerBetween.get(i);
            TerrainProfile terrainProfileB = allByLineNumberIsAndKilometerBetween.get(i + 1);

            double heightA = terrainProfileA.getHeight();
            double heightB = terrainProfileB.getHeight();

            double slope = profile(heightA, heightB);
            terrainProfileA.setSlope(slope);
        }
        return allByLineNumberIsAndKilometerBetween;

    }


    private double profile(double heightA, double heightB) {
        double v = heightB - heightA;
        return (v / 100) * 1000;
    }

}
