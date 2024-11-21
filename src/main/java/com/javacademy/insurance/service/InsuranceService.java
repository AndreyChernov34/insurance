package com.javacademy.insurance.service;

import com.javacademy.insurance.insurence_objects.InsuranceType;
import com.javacademy.insurance.exceptions.NonExistentNumberContract;
import com.javacademy.insurance.insurence_objects.InsuranceContract;

import java.math.BigDecimal;

public interface InsuranceService {
    InsuranceContract insuranceOffer(BigDecimal coverageAmount, String name, InsuranceType insuranceType);
    InsuranceContract insurancePay(String numberInsuranceContract) throws NonExistentNumberContract;
}
