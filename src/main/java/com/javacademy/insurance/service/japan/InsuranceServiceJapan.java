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

/**
 * Сервис страхования для Японии
 */
@Component
@Profile("japan")
@EnableConfigurationProperties(Property.class)
@AllArgsConstructor
public class InsuranceServiceJapan implements InsuranceService {
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
    InsuranceCalcJapanService insuranceCalcJapanService;

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
