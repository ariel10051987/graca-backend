package com.projeto.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GracaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GracaApplication.class, args);
    }

}