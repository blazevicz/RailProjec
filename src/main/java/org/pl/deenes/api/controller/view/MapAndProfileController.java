package org.pl.deenes.api.controller.view;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.api.controller.dto.TerrainProfileDTO;
import org.pl.deenes.api.controller.mapper.TerrainProfileDTOMapper;
import org.pl.deenes.infrastructure.repositories.TrainRepository;
import org.pl.deenes.model.TerrainProfile;
import org.pl.deenes.model.Train;
import org.pl.deenes.model.TrainStats;
import org.pl.deenes.osm.model.Way;
import org.pl.deenes.services.ApiService;
import org.pl.deenes.services.TerrainProfileServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
@Slf4j
public class MapAndProfileController {

    private final TerrainProfileServiceImpl terrainProfileService;
    private final TerrainProfileDTOMapper terrainProfileDTOMapper;
    private final TrainRepository trainRepository;
    private final ApiService apiService;

    @GetMapping("/trains/{trainKwr}/profile")
    String trainDetails(@PathVariable Integer trainKwr, @NonNull Model model) {

        Train train = trainRepository.find(trainKwr).orElseThrow();
        LinkedList<TerrainProfile> terrainProfileLinkedList = getTerrainProfiles(train);

        List<TerrainProfileDTO> terrainProfileDTOList = terrainProfileLinkedList.stream()
                .map(terrainProfileDTOMapper::mapToDTO)
                .toList();

        Set<TrainStats> trainStats = train.getTrainStats();
        log.warn(trainStats.toString());
        List<List<Way>> listWithStationInfo1 = trainStats.stream().map(a -> apiService.findStationAndGetPosition(a.getStation())).toList();

        List<Way> listWithStationInfo = listWithStationInfo1.stream()
                .flatMap(List::stream)
                .toList();
        log.warn(listWithStationInfo.toString());

        model.addAttribute("terrainProfiles", terrainProfileDTOList);
        model.addAttribute("listWithStationInfo", listWithStationInfo);
        return "map_profile";
    }

    @NonNull
    private LinkedList<TerrainProfile> getTerrainProfiles(@NonNull Train train) {
        Set<TrainStats> allTrainsStats = train.getTrainStats();

        LinkedList<TerrainProfile> terrainProfileLinkedList = new LinkedList<>();

        allTrainsStats.forEach(trainStats -> {
            List<TerrainProfile> terrainProfiles = terrainProfileService.calculateSlope(
                    trainStats.getLineNumber(),
                    trainStats.getFirstKilometer(),
                    trainStats.getLastKilometer()
            );
            terrainProfileLinkedList.addAll(terrainProfiles);
        });
        return terrainProfileLinkedList;
    }
}
