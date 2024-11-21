package com.javacademy.insurance.service.japan;

import com.javacademy.insurance.Archive;
import com.javacademy.insurance.insurence_objects.Property;
import com.javacademy.insurance.service.NumberContractGenerator;
import com.javacademy.insurance.exceptions.NonExistentNumberContract;
import com.javacademy.insurance.insurence_objects.ContractStatus;
import com.javacademy.insurance.insurence_objects.InsuranceContract;
import com.javacademy.insurance.insurence_objects.InsuranceType;
import com.javacademy.insurance.service.InsuranceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("japan")
@EnableConfigurationProperties(Property.class)
@AllArgsConstructor
public class InsuranceServiceJapan implements InsuranceService {
    @Autowired
    private Property property;
    @Autowired
    private Archive archive;
    @Autowired
    private NumberContractGenerator numberContractGenerator;
    @Autowired
    InsuranceCalcJapanService insuranceCalcJapanService;

    @Override
    public InsuranceContract insuranceOffer(BigDecimal coverageAmount, String name, InsuranceType insuranceType) {

        InsuranceContract resultInsurance;


        resultInsurance = new InsuranceContract(numberContractGenerator.generateContractNumber(),
                insuranceCalcJapanService.insuranceCost(coverageAmount, insuranceType),
                coverageAmount,
                property.getCurrency(),
                name,
                property.getCountry(),
                ContractStatus.UNPAID_CONTRACT,
                insuranceType);
        archive.addContract(resultInsurance);
        return resultInsurance;
    }

    @Override
    public InsuranceContract insurancePay(String numberInsuranceContract) throws NonExistentNumberContract {

        if (!archive.contractContains(numberInsuranceContract)) {
            throw new NonExistentNumberContract("такого договора не существует");
        } else {
            InsuranceContract resultInsurance;
            resultInsurance = archive.getContract(numberInsuranceContract);
            resultInsurance.setContractStatus(ContractStatus.PAID_CONTRACT);
            return resultInsurance;
        }
    }
}
