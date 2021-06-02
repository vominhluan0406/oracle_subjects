package com.luanvo.coincat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoincatApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoincatApplication.class, args);
    }

}
