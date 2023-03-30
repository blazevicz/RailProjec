package org.pl.deenes.Configuration;


import org.pl.deenes.Main;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = Main.class)
public class SpringConfiguration {
}
