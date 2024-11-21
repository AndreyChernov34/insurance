package com.javacademy.insurance.service.brazil;

import com.javacademy.insurance.insurence_objects.InsuranceType;
import com.javacademy.insurance.insurence_objects.Property;
import com.javacademy.insurance.service.InsuranceCalcService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
@Profile("brazil")
@AllArgsConstructor
@EnableConfigurationProperties(Property.class)
public class InsuranceCalcBrazilService implements InsuranceCalcService {
    Property property;

    @Override
    public BigDecimal insuranceCost(BigDecimal coverageAmount, InsuranceType insuranceType) {
        BigDecimal result = BigDecimal.ZERO;
        switch (insuranceType) {
            case HEALTH_INSURANCE -> {
                result = coverageAmount.multiply(property.getHealthcoefficient()).
                        add(property.getHealthamount());
            }
            case ROBBERY_INSURANCE -> {
                result = coverageAmount.multiply(property.getRobberycoefficient()).
                        add(property.getRobberyamount());
            }
            default -> {
                throw new IllegalArgumentException("Неизвестный тип страхования");
            }
        }
        return result;
    }
}
