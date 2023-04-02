package org.pl.deenes;


import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.configuration.SpringConfiguration;
import org.pl.deenes.services.Result;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        Result bean = applicationContext.getBean(Result.class);
        bean.runningMethod();

    }
}