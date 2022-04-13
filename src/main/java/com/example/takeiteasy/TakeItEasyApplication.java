package com.example.takeiteasy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
//@ComponentScan(basePackages = {"com.example.takeiteasy.controllers"})
@SpringBootApplication
public class TakeItEasyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TakeItEasyApplication.class, args);
    }

}
