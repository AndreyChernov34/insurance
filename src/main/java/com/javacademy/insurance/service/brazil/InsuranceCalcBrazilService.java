package com.javacademy.insurance.service.brazil;


import com.javacademy.insurance.insurence_objects.Property;
import com.javacademy.insurance.service.AbstractInsuranceCalcService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Страховой калькулятор для Бразилии
 */
@Component
@Profile("brazil")
@EnableConfigurationProperties(Property.class)
public class InsuranceCalcBrazilService extends AbstractInsuranceCalcService {
    public InsuranceCalcBrazilService(Property property) {
        super(property);
    }
}
