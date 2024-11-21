package com.javacademy.insurance.exceptions;
/**
 * Вызываемое исключение если контракт не найден по номеру
 */
public class NonExistentNumberContract extends Exception {
    public NonExistentNumberContract(String message) {
        super(message);
    }
}
