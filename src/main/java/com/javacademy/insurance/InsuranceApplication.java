package com.javacademy.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class InsuranceApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(InsuranceApplication.class, args);
    }
}
