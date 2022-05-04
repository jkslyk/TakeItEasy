package com.example.takeiteasy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.example.takeiteasy"})
public class TakeItEasyApplication {
    public static void main(String[] args) {
        SpringApplication.run(TakeItEasyApplication.class, args);
    }

}
