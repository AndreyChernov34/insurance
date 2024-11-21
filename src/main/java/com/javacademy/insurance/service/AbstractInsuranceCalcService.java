package com.javacademy.insurance.service;

import com.javacademy.insurance.insurence_objects.InsuranceType;
import com.javacademy.insurance.insurence_objects.Property;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.math.BigDecimal;

/**
 * Страховой калькулятор
 */

@AllArgsConstructor
@EnableConfigurationProperties(Property.class)
public abstract class AbstractInsuranceCalcService implements com.javacademy.insurance.service.InsuranceCalcService {
    Property property;
    /**
     * метод расчета стоимости страховки
     * @param coverageAmount
     * @param insuranceType
     * @return
     */
    @Override
    public BigDecimal insuranceCost(BigDecimal coverageAmount, InsuranceType insuranceType) {
        BigDecimal result = BigDecimal.ZERO;
        switch (insuranceType) {
            case HEALTH_INSURANCE -> {
                result = coverageAmount.multiply(property.getHealthCoefficient()).
                        add(property.getHealthAmount());
            }
            case ROBBERY_INSURANCE -> {
                result = coverageAmount.multiply(property.getRobberyCoefficient()).
                        add(property.getRobberyAmount());
            }
            default -> {
                throw new IllegalArgumentException("Неизвестный тип страхования");
            }
        }
        return result;
    }
}
