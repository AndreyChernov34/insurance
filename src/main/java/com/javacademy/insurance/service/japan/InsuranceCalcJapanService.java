package com.javacademy.insurance.service.japan;


import com.javacademy.insurance.insurence_objects.Property;
import com.javacademy.insurance.service.AbstractInsuranceCalcService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Страховой калькулятор для Японии
 */
@Component
@Profile("japan")
@EnableConfigurationProperties(Property.class)
public class InsuranceCalcJapanService extends AbstractInsuranceCalcService {
    public InsuranceCalcJapanService(Property property) {
        super(property);
    }
}
