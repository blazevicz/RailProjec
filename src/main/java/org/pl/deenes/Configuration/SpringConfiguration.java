package org.pl.deenes.Configuration;


import org.pl.deenes.Main;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = Main.class)
@AutoConfiguration
public class SpringConfiguration {
}
