package org.pl.deenes.api.controller.view;

import lombok.AllArgsConstructor;
import org.pl.deenes.api.controller.dto.TrainDTO;
import org.pl.deenes.api.controller.exception.NotFound;
import org.pl.deenes.api.controller.mapper.TrainDTOMapper;
import org.pl.deenes.infrastructure.repositories.TrainRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class MapAndProfileController {

    private final TrainDTOMapper trainMapper;
    private final TrainRepository trainRepository;


    @GetMapping(value = "/trains/{trainKwr}/profile")
    public String trainDetails(@PathVariable Integer trainKwr, Model model) {

        var train = trainRepository.find(trainKwr)
                .orElseThrow(() -> new NotFound("Train trainKwr: %s not found".formatted(trainKwr)));
        TrainDTO trainDTO = trainMapper.mapToDTO(train);
        model.addAttribute("existingTrains", trainDTO);

        return "map_profile";
    }


}
