package com.javacademy.insurance.service;

import com.javacademy.insurance.insurence_objects.InsuranceType;

import java.math.BigDecimal;

public interface InsuranceCalcService {
    BigDecimal insuranceCost(BigDecimal coverageAmount, InsuranceType insuranceType) throws IllegalArgumentException;
    }

