package org.pl.deenes;


import org.pl.deenes.services.ResultServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


@SpringBootApplication
public class Main {
    public static void main(String[] args) throws IOException {
        Logger.getLogger("org.apache.pdfbox").setLevel(Level.SEVERE);
        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);

        ResultServiceImpl bean = run.getBean(ResultServiceImpl.class);
        bean.runningMethod();

/*
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        WOSReaderService bean1 = applicationContext.getBean(WOSReaderService.class);
        bean1.loadWosPDF();
*/


     /*   AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        ResultServiceImpl bean = applicationContext.getBean(ResultServiceImpl.class);
        WOSReader bean1 = applicationContext.getBean(WOSReader.class);
        //bean.runningMethod();
        //bean1.loadWosPDF();
        bean1.loadRailwayProfileFromPDF();*/
        /*WebScrappingRegions bean2 = applicationContext.getBean(WebScrappingRegions.class);
        bean2.runner();*/

    }
}