package org.pl.deenes.api.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.pl.deenes.api.controller.dto.DispatcherDTO;
import org.pl.deenes.api.controller.mapper.DispatcherDTOMapper;
import org.pl.deenes.infrastructure.repositories.DispatcherRepository;
import org.pl.deenes.model.Dispatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/dispatcher")
public class DispatcherRestController {

    private final DispatcherDTOMapper dispatcherDTOMapper;
    private final DispatcherRepository dispatcherRepository;

    @GetMapping(value = "/all")
    public List<DispatcherDTO> allDrivers() {
        return dispatcherRepository.findAll().stream()
                .map(dispatcherDTOMapper::mapToDTO)
                .toList();
    }

    @PostMapping(value = "/add")
    public DispatcherDTO addDispatcher(@RequestBody DispatcherDTO dispatcherDTO) {
        Dispatcher save = dispatcherRepository.save(dispatcherDTOMapper.mapFromDTO(dispatcherDTO));
        return dispatcherDTOMapper.mapToDTO(save);
    }
}
