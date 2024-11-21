package com.javacademy.insurance.service;

import com.javacademy.insurance.Archive;
import com.javacademy.insurance.exceptions.NonExistentNumberContract;
import com.javacademy.insurance.insurence_objects.ContractStatus;
import com.javacademy.insurance.insurence_objects.InsuranceContract;
import com.javacademy.insurance.insurence_objects.InsuranceType;
import com.javacademy.insurance.insurence_objects.Property;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.math.BigDecimal;

/**
 * Сервис страхования
 */

@EnableConfigurationProperties(Property.class)
@AllArgsConstructor
public abstract class AbstractInsuranceService implements com.javacademy.insurance.service.InsuranceService {
    // класс с данными
    @Autowired
    private Property property;
    // архив
    @Autowired
    private Archive archive;
    // генератор номера
    @Autowired
    private NumberContractGenerator numberContractGenerator;
    // Страховой калькулятор
    @Autowired
    AbstractInsuranceCalcService abstractInsuranceCalcService;

    @Override
    public InsuranceContract insuranceOffer(BigDecimal coverageAmount, String name, InsuranceType insuranceType) {

        InsuranceContract resultInsurance;

         /**
         * метод создания страхового предложения (неоплаченного контракта) с занесением в архив
         * @param coverageAmount    стоимость страхового покрытия
         * @param name              ФИО клиента
         * @param insuranceType     тип страхования
         * @return                  Страховой контракт (неоплаченный)
         */
        resultInsurance = new InsuranceContract(numberContractGenerator.generateContractNumber(),
                abstractInsuranceCalcService.insuranceCost(coverageAmount, insuranceType),
                coverageAmount,
                property.getCurrency(),
                name,
                property.getCountry(),
                ContractStatus.UNPAID_CONTRACT,
                insuranceType);
        archive.addContract(resultInsurance);
        return resultInsurance;
    }

    /**
     * метод оплаты страхового контракта
     * @param numberInsuranceContract       номер неоплаченного страхового контракта
     * @return                              оплаченный контракт
     * @throws NonExistentNumberContract    вызываемое исключение если контракт не найден по номеру
     */
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
