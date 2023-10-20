package org.pl.deenes.api.controller.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Your token is correct!");
    }

    @GetMapping("/session")
    public ResponseEntity<String> sessionCheck(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            return ResponseEntity.ok("Sesja istnieje.");
        } else {
            return ResponseEntity.ok("Sesja nie istnieje.");
        }
    }
}
