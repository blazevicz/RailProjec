package org.pl.deenes;


import org.pl.deenes.configuration.SpringConfiguration;
import org.pl.deenes.services.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Level;
import java.util.logging.Logger;


@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        Logger.getLogger("org.apache.pdfbox").setLevel(Level.SEVERE);

        SpringApplication.run(Main.class, args);

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        Result bean = applicationContext.getBean(Result.class);
        bean.runningMethod();

    }


}