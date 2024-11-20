package com.javacademy.insurance.Interfaces;

import com.javacademy.insurance.Enum.TypeInsurance;
import com.javacademy.insurance.Exceptions.NonExistentNumberContract;
import com.javacademy.insurance.InsuranceContract;

import java.math.BigDecimal;

public interface InsuranceService {
    InsuranceContract insuranceOffer(BigDecimal coverageAmount, String name, TypeInsurance typeInsurance);
    InsuranceContract insurancePay(String numberInsuranceContract) throws NonExistentNumberContract;
}
