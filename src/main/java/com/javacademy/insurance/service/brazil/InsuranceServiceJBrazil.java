package com.javacademy.insurance.service.brazil;

import com.javacademy.insurance.Archive;
import com.javacademy.insurance.insurence_objects.Property;
import com.javacademy.insurance.service.NumberContractGenerator;
import com.javacademy.insurance.service.AbstractInsuranceCalcService;
import com.javacademy.insurance.service.AbstractInsuranceService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Сервис страхования для Бразилии
 */
@Component
@Profile("brazil")
@EnableConfigurationProperties(Property.class)
public class InsuranceServiceJBrazil extends AbstractInsuranceService {

    public InsuranceServiceJBrazil(Property property, Archive archive, NumberContractGenerator numberContractGenerator,
                                   AbstractInsuranceCalcService abstractInsuranceCalcService) {
        super(property, archive, numberContractGenerator, abstractInsuranceCalcService);
    }

}
