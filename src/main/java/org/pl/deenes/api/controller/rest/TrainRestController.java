package org.pl.deenes.api.controller.rest;


import lombok.AllArgsConstructor;
import org.pl.deenes.api.controller.dto.TrainDTO;
import org.pl.deenes.api.controller.mapper.TrainDTOMapper;
import org.pl.deenes.infrastructure.repositories.TrainRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/train")
public class TrainRestController {

    private final TrainDTOMapper trainDTOMapper;
    private final TrainRepository trainRepository;

    @GetMapping(value = "/{trainKwr}")
    public List<TrainDTO> trainInfo(@PathVariable Integer trainKwr) {

        var train = trainRepository.find(trainKwr);
        //.orElseThrow(() -> new NotFound("Train kwr: %s not found".formatted(trainKwr)));
        return train.stream().map(trainDTOMapper::mapToDTO).toList();
    }

    @DeleteMapping(value = "/delete/{trainKwr}")
    public void deleteTrain(@PathVariable Integer trainKwr) {
        trainRepository.delete(trainKwr);
    }

}
