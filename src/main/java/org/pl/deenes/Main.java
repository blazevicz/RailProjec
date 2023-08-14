package org.pl.deenes;

import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.infrastructure.repositories.dao.TrainDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


@SpringBootApplication
@Slf4j
public class Main {
    public static void main(String[] args) throws IOException {
        Logger.getLogger("org.apache.pdfbox").setLevel(Level.SEVERE);
        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);


        TrainDAO bean1 = run.getBean(TrainDAO.class);
        // var train1 = bean1.find(666401);


    }
}
