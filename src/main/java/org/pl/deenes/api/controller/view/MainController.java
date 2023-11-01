package org.pl.deenes.api.controller.view;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
@Hidden
public class MainController {

    @GetMapping(value = "/")
    public String mainPage() {
        return "index";
    }
}
