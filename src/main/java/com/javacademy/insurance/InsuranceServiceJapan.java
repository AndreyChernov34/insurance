package com.javacademy.insurance;

import com.javacademy.insurance.Enum.ContractStatus;
import com.javacademy.insurance.Enum.TypeInsurance;
import com.javacademy.insurance.Exceptions.NonExistentNumberContract;
import com.javacademy.insurance.Interfaces.InsuranceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("japan")
public class InsuranceServiceJapan implements InsuranceService {
    @Value("${country}")
    private String country;
    @Value("${currency}")
    private String currency;
    Archive archive;


    @Override
    public InsuranceContract insuranceOffer(BigDecimal coverageAmount, String name, TypeInsurance typeInsurance) {

        InsuranceContract resultInsurance;
        InsuranceCalcJapanService insuranceCalcJapanService = new InsuranceCalcJapanService();
        NumberContractGenerator numberContractGenerator = new NumberContractGenerator();
        String number = numberContractGenerator.generateContractNumber();

        resultInsurance = new InsuranceContract(number,
                insuranceCalcJapanService.insuranceCost(coverageAmount, typeInsurance),
                coverageAmount,
                currency,
                name,
                country,
                ContractStatus.UNPAID_CONTRACT,
                typeInsurance);

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
