package com.javacademy.insurance.service.japan;

import com.javacademy.insurance.insurence_objects.InsuranceType;
import com.javacademy.insurance.insurence_objects.Property;
import com.javacademy.insurance.service.InsuranceCalcService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Страховой калькулятор для Японии
 */
@Component
@Profile("japan")
@AllArgsConstructor
@EnableConfigurationProperties(Property.class)
public class InsuranceCalcJapanService implements InsuranceCalcService {
    Property property;

    /**
     * метод расчета стоимости страховки
     * @param coverageAmount
     * @param insuranceType
     * @return
     */
    @Override
    public BigDecimal insuranceCost(BigDecimal coverageAmount, InsuranceType insuranceType)
            throws IllegalArgumentException {
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
