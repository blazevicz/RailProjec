package org.pl.deenes.api.controller.rest;


import lombok.AllArgsConstructor;
import org.pl.deenes.api.controller.dto.TrainDTO;
import org.pl.deenes.api.controller.exception.NotFound;
import org.pl.deenes.api.controller.mapper.TrainDTOMapper;
import org.pl.deenes.infrastructure.repositories.TrainRepository;
import org.pl.deenes.model.Train;
import org.springframework.web.bind.annotation.*;

@RestController
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
