package org.pl.deenes.api.controller.view;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.infrastructure.entity.CautionEntity;
import org.pl.deenes.infrastructure.mapper.CautionMapper;
import org.pl.deenes.model.Caution;
import org.pl.deenes.services.WarningsReaderServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class DispatcherController {
    private final WarningsReaderServiceImpl warningsReaderService;
    private final CautionMapper cautionMapper;

    @GetMapping("/dispatcher/read_warnings")
    String readAllWarnings(@NonNull Model model) {
        List<CautionEntity> cautions = warningsReaderService.loadWarningsFromPDF();
        List<Caution> list = cautions.stream().map(cautionMapper::mapFromEntity).toList();

        model.addAttribute("allWarnings", list);

        return "all_warnings";
    }

}
