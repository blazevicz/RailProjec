package org.pl.deenes.api.controller.view;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.pl.deenes.api.controller.dto.DriverDTO;
import org.pl.deenes.api.controller.mapper.DriverDTOMapper;
import org.pl.deenes.infrastructure.repositories.DriverRepository;
import org.pl.deenes.model.Driver;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;

@Controller
@AllArgsConstructor
public class DriverController {

    private static final String SUCCESS_VIEW = "driver_success";
    private final DriverDTOMapper driverDTOMapper;
    private final DriverRepository driverRepository;

    @GetMapping(value = "/drivers")
    public String drivers(@NonNull Model model) {

        List<Driver> allDrivers = driverRepository.findAllDrivers();
        List<DriverDTO> list = allDrivers.stream().map(driverDTOMapper::mapToDTO).toList();
        model.addAttribute("driversDTO", list);
        return "drivers";
    }

    @GetMapping(value = "/driver/{driverId}")
    public String driverById(@PathVariable Integer driverId, @NonNull Model model) {

        var driverById = driverRepository.findDriverById(driverId).orElseThrow();
        DriverDTO driverDTO = driverDTOMapper.mapToDTO(driverById);
        model.addAttribute("driverById", driverDTO);
        return "driver";
    }

    @PostMapping(value = "/driver")
    public String createDriver(@RequestBody DriverDTO driverDTO) {
        Driver driver = driverDTOMapper.mapFromDTO(driverDTO);
        driverRepository.save(driver);
        return SUCCESS_VIEW;
    }

    @PutMapping(value = "/driver/{driverId}")
    public String updateDriver(@PathVariable Integer driverId, @RequestBody DriverDTO driverDTO) throws ChangeSetPersister.NotFoundException {
        var driverById = driverRepository.findDriverById(driverId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        Driver driver = driverDTOMapper.mapFromDTO(driverDTO);
        driverById.setName(driver.getName());
        driverById.setSurname(driver.getSurname());
        driverById.setPesel(driver.getPesel());
        driverRepository.save(driverById);
        return SUCCESS_VIEW;
    }

    @DeleteMapping(value = "/driver/{driverId}")
    public String deleteDriver(@PathVariable Integer driverId) {
        Driver driver = driverRepository.findDriverById(driverId)
                .orElseThrow(() -> new NotFoundException("Driver not found: " + driverId));

        driverRepository.delete(driver.getDriverId());
        return "redirect:/drivers";
    }
}
