package org.pl.deenes;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


@SpringBootApplication
public class Main {
    public static void main(String[] args) throws IOException {
        Logger.getLogger("org.apache.pdfbox").setLevel(Level.SEVERE);

        SpringApplication.run(Main.class, args);

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