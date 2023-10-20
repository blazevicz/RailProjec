package org.pl.deenes.cfg;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.pl.deenes.api.controller.rest.TrainRestController;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocCfg {
    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("default")
                .pathsToMatch("/**").packagesToScan(TrainRestController.class.getPackageName()).build();
    }

    @Bean
    public OpenAPI springDocOpenApi() {
        return new OpenAPI()
                .components(new Components()).info(new Info()
                        .title("Train driver helper").contact(contact()).version("0.01"));
    }

    private Contact contact() {
        return new Contact()
                .name("Damian")
                .email("dddnsss//at//gmail.com");
    }
}