package org.pl.deenes.api.controller;


import lombok.AllArgsConstructor;
import org.pl.deenes.api.controller.dto.TrainDTO;
import org.pl.deenes.api.controller.exception.NotFound;
import org.pl.deenes.api.controller.mapper.TrainDTOMapper;
import org.pl.deenes.infrastructure.repositories.TrainRepository;
import org.pl.deenes.model.Train;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/api/train")
public class TrainRestController {

    private final TrainDTOMapper trainDTOMapper;
    private final TrainRepository trainRepository;

    @GetMapping(value = "/{trainKwr}")
    public TrainDTO trainInfo(@PathVariable Integer trainKwr) {

        Train train = trainRepository.find(trainKwr).orElseThrow(() -> new NotFound("Train kwr: %s not found".formatted(trainKwr)));
        return trainDTOMapper.mapToDTO(train);
    }

    @DeleteMapping(value = "/delete/{trainKwr}")
    public void deleteTrain(@PathVariable Integer trainKwr) {
        trainRepository.delete(trainKwr);
    }

}
