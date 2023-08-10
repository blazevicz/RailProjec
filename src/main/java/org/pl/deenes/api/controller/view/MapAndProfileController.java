package org.pl.deenes.api.controller.view;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.api.controller.dto.TerrainProfileDTO;
import org.pl.deenes.api.controller.mapper.TerrainProfileDTOMapper;
import org.pl.deenes.infrastructure.repositories.AnalyseRepository;
import org.pl.deenes.infrastructure.repositories.TrainStatsRepository;
import org.pl.deenes.model.Analyse;
import org.pl.deenes.model.TerrainProfile;
import org.pl.deenes.model.TrainStats;
import org.pl.deenes.services.TerrainProfileServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedList;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class MapAndProfileController {

    private final TerrainProfileServiceImpl terrainProfileService;
    private final AnalyseRepository analyseRepository;
    private final TerrainProfileDTOMapper terrainProfileDTOMapper;
    private final TrainStatsRepository trainStatsRepository;
    //private final CautionRepository cautionRepository;


    @GetMapping("/trains/{trainKwr}/profile")
    String trainDetails(@PathVariable Integer trainKwr, @NonNull Model model) {

        // List<Analyse> allByTrainKwr = analyseRepository.findAllByTrainKwr(trainKwr);

        List<TrainStats> trainStatsList = retrieveTrainStatsList(trainKwr);

        LinkedList<TerrainProfile> terrainProfileLinkedList = new LinkedList<>();

        trainStatsList.forEach(trainStats -> {
            List<TerrainProfile> terrainProfiles = terrainProfileService.calculateSlope(
                    trainStats.getLineNumber(),
                    trainStats.getFirstKilometer(),
                    trainStats.getLastKilometer()
            );
            terrainProfileLinkedList.addAll(terrainProfiles);
        });

        List<TerrainProfileDTO> terrainProfileDTOList = terrainProfileLinkedList.stream()
                .map(terrainProfileDTOMapper::mapToDTO)
                .toList();

        model.addAttribute("terrainProfiles", terrainProfileDTOList);
        return "map_profile";
    }

    private List<TrainStats> retrieveTrainStatsList(Integer trainKwr) {
        List<Analyse> allByTrainKwr = analyseRepository.findAllByTrainKwr(trainKwr);
        List<Integer> trainStatsIds = allByTrainKwr.stream()
                .map(Analyse::getTrainAnalyseId)
                .toList();

        return trainStatsIds.stream()
                .skip(1)
                .map(trainStatsId -> trainStatsRepository.findAllByTrainStatsId(trainStatsId).orElseThrow())
                .toList();
    }
}
