package com.javacademy.insurance;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NumberContractGenerator {

    public String generateContractNumber() {
        return "CONTRACT-" + UUID.randomUUID().toString();
    }
}
