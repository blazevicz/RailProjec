package org.pl.deenes.api.controller.view;

import lombok.AllArgsConstructor;
import lombok.NonNull;
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
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class MapAndProfileController {

    private final TerrainProfileServiceImpl terrainProfileService;
    private final AnalyseRepository analyseRepository;
    private final TerrainProfileDTOMapper terrainProfileDTOMapper;
    private final TrainStatsRepository trainStatsRepository;


    @GetMapping(value = "/trains/{trainKwr}/profile")
    String trainDetails(@PathVariable Integer trainKwr, @NonNull Model model) {
        LinkedList<TrainStats> listId = new LinkedList<>();

        List<Analyse> allByTrainKwr = analyseRepository.findAllByTrainKwr(trainKwr);
        var trainStats =
                new java.util.ArrayList<>(allByTrainKwr.stream().map(Analyse::getTrainAnalyseId).toList());

        trainStats.stream().skip(1).forEach(a -> {
            TrainStats allByTrainStatsId = trainStatsRepository.findAllByTrainStatsId(a).orElseThrow();
            listId.add(allByTrainStatsId);

        });
        LinkedList<TerrainProfile> terrainProfileLinkedList = new LinkedList<>();

        listId.forEach(a -> {
            List<TerrainProfile> terrainProfiles = terrainProfileService.calculateSlope(a.getLineNumber(), a.getFirstKilometer(), a.getLastKilometer());
            terrainProfileLinkedList.addAll(terrainProfiles);
        });

        var terrainProfileDTO =
                terrainProfileLinkedList.stream()
                        .map(terrainProfileDTOMapper::mapToDTO)
                        .collect(Collectors.toCollection(LinkedList::new));


        model.addAttribute("terrainProfiles", terrainProfileDTO);
        return "map_profile";
    }
}
