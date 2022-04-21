package com.example.takeiteasy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
//@ComponentScan(basePackages = {"com.example.takeiteasy.controllers"})
@SpringBootApplication
public class TakeItEasyApplication {
    public static void main(String[] args) {
        SpringApplication.run(TakeItEasyApplication.class, args);
    }

}
