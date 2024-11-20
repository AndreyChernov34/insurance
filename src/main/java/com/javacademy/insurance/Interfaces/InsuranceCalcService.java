package com.javacademy.insurance.Interfaces;

import com.javacademy.insurance.Enum.TypeInsurance;

import java.math.BigDecimal;

public interface InsuranceCalcService {
    BigDecimal insuranceCost(BigDecimal coverageAmount, TypeInsurance typeInsurance);
    }

