package org.pl.deenes.api.controller.view;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.pl.deenes.api.controller.dto.DriverDTO;
import org.pl.deenes.api.controller.dto.TrainDTO;
import org.pl.deenes.api.controller.mapper.DriverDTOMapper;
import org.pl.deenes.api.controller.mapper.TrainDTOMapper;
import org.pl.deenes.infrastructure.repositories.DriverRepository;
import org.pl.deenes.infrastructure.repositories.TrainRepository;
import org.pl.deenes.model.Driver;
import org.pl.deenes.model.Train;
import org.pl.deenes.services.ResultServiceImpl;
import org.pl.deenes.services.interfaces.TrainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Hidden
public class TrainController {

    private final DriverDTOMapper driverMapper;
    private final TrainDTOMapper trainMapper;
    private final TrainRepository trainRepository;
    private final DriverRepository driverRepository;
    private final ResultServiceImpl resultService;
    private final TrainService trainService;

    @GetMapping(value = "/trains")
    public String trainsPage(@NonNull Model model) {
        List<Train> all = trainRepository.findAll();
        List<TrainDTO> trainsList = all.stream().map(trainMapper::mapToDTO).toList();

        model.addAttribute("existingTrains", trainsList);
        List<Driver> allDrivers = driverRepository.findAllDrivers();
        List<DriverDTO> driversList = allDrivers.stream().map(driverMapper::mapToDTO).toList();
        model.addAttribute("existingDrivers", driversList);
        model.addAttribute("pdfFiles", List.of(
                "RJ_SKRJ_666401_464028_9.pdf",
                "RJ_SKRJ_956925_644010_1.pdf"
        ));
        return "train";
    }

    @PostMapping("/trains/add")
    public String uploadNewTrain(@RequestParam("pdfLink") String pdfLink) {
        String s = "src/main/resources/pdfs/" + pdfLink;
        Train train = trainService.trainCreate(s);
        trainService.saveTrain(train);

        return "redirect:/trains";
    }

    @GetMapping(value = "/trains/{trainKwr}")
    public String trainDetails(@PathVariable Integer trainKwr, @NonNull Model model) {
        Optional<Train> optionalTrain = trainRepository.find(trainKwr);

        if (optionalTrain.isPresent()) {
            Train train = optionalTrain.get();
            TrainDTO trainDTO = trainMapper.mapToDTO(train);
            model.addAttribute("train", trainDTO);
            return "trainDetails";
        } else {
            return "error";
        }
    }
}
