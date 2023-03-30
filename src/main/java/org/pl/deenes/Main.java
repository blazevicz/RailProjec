package org.pl.deenes;


import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.Configuration.SpringConfiguration;
import org.pl.deenes.Services.Result;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.text.ParseException;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        Result bean = applicationContext.getBean(Result.class);
        bean.voidTestingResultMethodForOneLine();

    }
}