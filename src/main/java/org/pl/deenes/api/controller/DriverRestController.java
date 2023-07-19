package org.pl.deenes.api.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.pl.deenes.api.controller.dto.DriverDTO;
import org.pl.deenes.api.controller.mapper.DriverDTOMapper;
import org.pl.deenes.infrastructure.repositories.DriverRepository;
import org.pl.deenes.model.Driver;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/driver")
public class DriverRestController {

    private final DriverDTOMapper driverDTOMapper;
    private final DriverRepository driverRepository;

    @GetMapping(value = "/all")
    public List<DriverDTO> allDrivers() {
        return driverRepository.findAllDrivers().stream()
                .map(driverDTOMapper::mapToDTO)
                .toList();
    }

    @GetMapping(value = "/{driverId}/")
    public DriverDTO findDriver(@PathVariable Integer driverId) {

        return driverRepository.findDriverById(driverId)
                .map(driverDTOMapper::mapToDTO)
                .orElseThrow();
    }

    @PostMapping(value = "/add")
    public DriverDTO addDriver(@RequestBody DriverDTO driverDTO) {
        Driver save = driverRepository.save(driverDTOMapper.mapFromDTO(driverDTO));
        return driverDTOMapper.mapToDTO(save);
    }

    @DeleteMapping(value = "/{driverId}/delete")
    public void deleteDriver(@PathVariable Integer driverId) {
        driverRepository.delete(driverId);
    }


}
