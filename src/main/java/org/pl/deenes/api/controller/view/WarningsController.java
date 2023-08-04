package org.pl.deenes.api.controller.view;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.pl.deenes.api.controller.dto.RegionDTO;
import org.pl.deenes.api.controller.mapper.RegionDTOMapper;
import org.pl.deenes.infrastructure.repositories.RegionRepository;
import org.pl.deenes.model.Region;
import org.pl.deenes.services.WebScrapingRegionsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class WarningsController {
    private final RegionRepository regionRepository;
    private final RegionDTOMapper regionDTOMapper;
    private final WebScrapingRegionsService webScrapingRegionsService;

    @GetMapping(path = "/warnings")
    public String downloadWarnings(@NonNull Model model) {
        webScrapingRegionsService.runner();
        List<Region> all = regionRepository.findAll();
        List<RegionDTO> list = all.stream().map(regionDTOMapper::mapToDTO).toList();

        model.addAttribute("warningsList", list);

        return "warnings";
    }

}
