package com.javacademy.insurance;

import com.javacademy.insurance.Enum.TypeInsurance;
import com.javacademy.insurance.Interfaces.InsuranceCalcService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("brazil")
public class InsuranceCalcBrazilServiceTest implements InsuranceCalcService {
    @Value("${app.health.coefficient")
    BigDecimal healthCoefficient;
    @Value("${app.robbery.coefficient")
    BigDecimal robberyCoefficient;
    @Value("${app.health.amount")
    BigDecimal healthAmount;
    @Value("${app.robbery.amount")
    BigDecimal robberyAmount;

    @Override
    public BigDecimal insuranceCost(BigDecimal coverageAmount, TypeInsurance typeInsurance) {
        BigDecimal result = BigDecimal.ZERO;
        switch (typeInsurance) {
            case HEALTH_INSURANCE -> {
                result = coverageAmount.multiply(healthCoefficient).add(healthAmount);
            }
            case ROBBERY_INSURANCE -> {
                result = coverageAmount.multiply(robberyCoefficient).add(robberyAmount);
            }
            default -> {

            }
        }
        return result;
    }
}
