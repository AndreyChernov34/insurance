package com.javacademy.insurance.service.brazil;

import com.javacademy.insurance.Archive;
import com.javacademy.insurance.insurence_objects.Property;
import com.javacademy.insurance.service.NumberContractGenerator;
import com.javacademy.insurance.exceptions.NonExistentNumberContract;
import com.javacademy.insurance.insurence_objects.ContractStatus;
import com.javacademy.insurance.insurence_objects.InsuranceContract;
import com.javacademy.insurance.insurence_objects.InsuranceType;
import com.javacademy.insurance.service.japan.InsuranceCalcJapanService;
import com.javacademy.insurance.service.InsuranceService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("brazil")
@EnableConfigurationProperties(Property.class)
public class InsuranceServiceJBrazil implements InsuranceService {
    Property property;
    Archive archive;


    @Override
    public InsuranceContract insuranceOffer(BigDecimal coverageAmount, String name, InsuranceType insuranceType) {

        InsuranceContract resultInsurance;
        InsuranceCalcJapanService insuranceCalcJapanService = new InsuranceCalcJapanService(property);
        NumberContractGenerator numberContractGenerator = new NumberContractGenerator();
        String number = numberContractGenerator.generateContractNumber();

        resultInsurance = new InsuranceContract(number,
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
