package org.pl.deenes;


import org.pl.deenes.services.ResultServiceImpl;
import org.pl.deenes.services.WOSReaderService;
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
//TODO: enum w db
/*
        SQLInsert oneInsert = sqlInserts()
                .tableName("emp")
                .column("id", intSeq().increment(10))
                .column("first_name", names().first(), TEXT_BACKSLASH)
                .column("last_name", names().last(), TEXT_BACKSLASH)
                .column("username", users(), TEXT_BACKSLASH)
                .column("email", emails(), TEXT_BACKSLASH)
                .column("description", markovs().size(32).type(LOREM_IPSUM), TEXT_BACKSLASH)
                .column("created", localDates().thisYear().display(BASIC_ISO_DATE), TEXT_BACKSLASH)
                .get();

        System.out.println(oneInsert);
*/

        WOSReaderService bean1 = run.getBean(WOSReaderService.class);
        bean1.loadWosPDF();


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