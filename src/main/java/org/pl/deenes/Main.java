package org.pl.deenes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Level;
import java.util.logging.Logger;


@SpringBootApplication
@Slf4j
public class Main {
    public static void main(String[] args) {
        Logger.getLogger("org.apache.pdfbox").setLevel(Level.SEVERE);
        SpringApplication.run(Main.class, args);

    }
}
