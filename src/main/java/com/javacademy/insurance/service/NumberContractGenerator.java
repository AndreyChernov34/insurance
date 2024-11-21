package com.javacademy.insurance.service;

import org.springframework.stereotype.Component;

/**
 * Генератор текстового номера страхового контракта
 */
@Component
public class NumberContractGenerator {
    private static int counter = 1;

    public String generateContractNumber() {
        return String.format("%03d", counter++);
    }
}
