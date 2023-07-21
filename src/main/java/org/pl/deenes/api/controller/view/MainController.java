package org.pl.deenes.api.controller.view;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MainController {

    @GetMapping(value = "/")
    public String mainPage() {
        return "mainPage";
    }
}
