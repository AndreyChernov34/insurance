package com.javacademy.insurance.service.japan;

import com.javacademy.insurance.Archive;
import com.javacademy.insurance.insurence_objects.Property;
import com.javacademy.insurance.service.NumberContractGenerator;
import com.javacademy.insurance.service.AbstractInsuranceCalcService;
import com.javacademy.insurance.service.AbstractInsuranceService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Сервис страхования для Японии
 */
@Component
@Profile("japan")
@EnableConfigurationProperties(Property.class)
public class InsuranceServiceJapan extends AbstractInsuranceService {
    public InsuranceServiceJapan(Property property, Archive archive, NumberContractGenerator numberContractGenerator,
                                 AbstractInsuranceCalcService abstractInsuranceCalcService) {
        super(property, archive, numberContractGenerator, abstractInsuranceCalcService);
    }
}
