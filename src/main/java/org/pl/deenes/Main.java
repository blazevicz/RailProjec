package org.pl.deenes;

import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.model.Train;
import org.pl.deenes.services.ApiService;
import org.pl.deenes.services.TrainServiceImpl;
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

        var bean = run.getBean(TrainServiceImpl.class);
        var bean2 = run.getBean(ApiService.class);
        // bean2.findStationAndGetPosition("BYTOM");

        Train train = bean.findTrain(666401);
        log.warn(train.toString());
        //System.out.println(train.getAnalyse().getTrainStats().size());


    }
}
