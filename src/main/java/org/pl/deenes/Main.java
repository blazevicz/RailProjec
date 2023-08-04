package org.pl.deenes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.logging.Level;
import java.util.logging.Logger;


//@ComponentScan(basePackageClasses = org.pl.deenes.Main.class)
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        Logger.getLogger("org.apache.pdfbox").setLevel(Level.SEVERE);
        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);

        //TrainServiceImpl bean = run.getBean(TrainServiceImpl.class);
        //Train train = bean.trainCreate();


    }
}
