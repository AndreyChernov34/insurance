package com.javacademy.insurance;

import com.javacademy.insurance.exceptions.NonExistentNumberContract;
import com.javacademy.insurance.insurence_objects.ContractStatus;
import com.javacademy.insurance.insurence_objects.CountryName;
import com.javacademy.insurance.insurence_objects.CurrencyName;
import com.javacademy.insurance.insurence_objects.InsuranceContract;
import com.javacademy.insurance.insurence_objects.InsuranceType;
import com.javacademy.insurance.insurence_objects.Property;
import com.javacademy.insurance.service.NumberContractGenerator;
import com.javacademy.insurance.service.japan.InsuranceCalcJapanService;
import com.javacademy.insurance.service.japan.InsuranceServiceJapan;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

import static org.mockito.Mockito.when;

/**
 * 10.3 Покрыть тестами InsuranceServiceJapan (калькулятор, архив, генератор номеров договоров - фальшивые)
 */
@ActiveProfiles("japan")
@SpringBootTest
public class InsuranceServiceJapanTest {
    @Autowired
    private Property property;
    @Autowired
    private  NumberContractGenerator numberContractGenerator;
    @Mock
    private  InsuranceCalcJapanService mockinsuranceCalcJapanServiceJapan;
    @Mock
    private Archive mockarchive;
    @Mock
    private NumberContractGenerator mocknumberContractGenerator;

    private static final BigDecimal COVERAGE_AMOUNT_ROBBERY = BigDecimal.valueOf(100000);
    private static final BigDecimal INSURANCE_COST_ROBBERY = BigDecimal.valueOf(20000);
    private static final BigDecimal COVERAGE_AMOUNT_HEALTH = BigDecimal.valueOf(10_000_000);
    private static final BigDecimal INSURANCE_COST_HEALTH = BigDecimal.valueOf(162000);

    private InsuranceServiceJapan insuranceServiceJapan;





    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**Ситуация №1: Получить предложение по страховке, на вход: Иванов Иван Иванович,
     * сумма покрытия 1 000 000, тип - от грабежа.
     * На выход страховка:
     * номер договора - 001 - получено от генератора номеров договоров
     * стоимость страховки - 20 000  - получено от заглушки калькулятора
     * сумма покрытия - 1 000 000
     * валюта договора - yen
     * ФИО клиента - Иванов Иван Иванович
     * страна действия - Japan
     * тип страховки: защита от грабежа
     * статусы договора:  неоплаченный договор     *
     */
    @Test
    public void insuranceContractRobberyTest() {
        String number = "001";

        InsuranceType insuranceType = InsuranceType.ROBBERY_INSURANCE;
        String name = "Иванов Иван Иванович";
        when(mocknumberContractGenerator.generateContractNumber()).thenReturn(number);
        when(mockinsuranceCalcJapanServiceJapan.insuranceCost(COVERAGE_AMOUNT_ROBBERY, insuranceType)).
                thenReturn(INSURANCE_COST_ROBBERY);

         insuranceServiceJapan = new InsuranceServiceJapan(property, mockarchive, mocknumberContractGenerator,
                 mockinsuranceCalcJapanServiceJapan);

        InsuranceContract expected = new InsuranceContract(number, INSURANCE_COST_ROBBERY,
                COVERAGE_AMOUNT_ROBBERY, CurrencyName.YEN, name, CountryName.JAPAN,
                ContractStatus.UNPAID_CONTRACT, insuranceType);
        InsuranceContract result = insuranceServiceJapan.insuranceOffer(COVERAGE_AMOUNT_ROBBERY, name, insuranceType);

        Assertions.assertEquals(expected, result);
    }

    /**
     * Ситуация №2: Получить предложение по страховке, на вход: Иванов Иван Иванович, сумма покрытия 10 000 000,
     * тип - от грабежа.
     * На выход страховка:
     * номер договора - 001 - получено от заглушки архива
     * стоимость страховки - 162 000  - получено от заглушки калькулятора
     * сумма покрытия - 10 000 000
     * валюта договора - yen
     * ФИО клиента - Иванов Иван Иванович
     * страна действия - Japan
     * тип страховки: мед страховка
     * статусы договора:  неоплаченный договор
     */
    @Test
    public void insuranceContractHealthTest() {
        String number = "001";
        ContractStatus contractStatus = ContractStatus.UNPAID_CONTRACT;
        CountryName countryName = CountryName.JAPAN;
        CurrencyName currencyName = CurrencyName.YEN;
        InsuranceType insuranceType = InsuranceType.HEALTH_INSURANCE;
        String name = "Иванов Иван Иванович";

        when(mocknumberContractGenerator.generateContractNumber()).thenReturn(number);
        when(mockinsuranceCalcJapanServiceJapan.insuranceCost(COVERAGE_AMOUNT_HEALTH, insuranceType)).
                thenReturn(INSURANCE_COST_HEALTH);
        insuranceServiceJapan = new InsuranceServiceJapan(property, mockarchive, mocknumberContractGenerator,
                mockinsuranceCalcJapanServiceJapan);

        InsuranceContract expected = new InsuranceContract(number, INSURANCE_COST_HEALTH,
                COVERAGE_AMOUNT_HEALTH, currencyName, name, countryName,
                contractStatus, insuranceType);
        InsuranceContract result = insuranceServiceJapan.insuranceOffer(COVERAGE_AMOUNT_HEALTH, name, insuranceType);
        Assertions.assertEquals(expected, result);
    }

    /**
     * Ситуация №3: Оплатить страховку, на вход: номер договора - 001.
     * Из заглушки архива получаем договор (т.е. создаем сами такой договор и заставляем архив вернуть такой договор):
     * номер договора - 001
     * стоимость страховки - 162 000
     * сумма покрытия - 10 000 000
     * валюта договора - yen
     * ФИО клиента - Иванов Иван Иванович
     * страна действия - Japan
     * тип страховки: мед страховка
     * статусы договора:  неоплаченный договор
     */
    @Test
    public void insuranceContractPayTest() throws NonExistentNumberContract {

        ContractStatus contractStatus = ContractStatus.PAID_CONTRACT;
        CountryName countryName = CountryName.JAPAN;
        CurrencyName currencyName = CurrencyName.YEN;
        InsuranceType insuranceType = InsuranceType.HEALTH_INSURANCE;
        String name = "Иванов Иван Иванович";
        String number = numberContractGenerator.generateContractNumber();

        when(mockarchive.contractContains(number)).thenReturn(true);
        when(mockarchive.getContract(number)).thenReturn(new InsuranceContract(number,
                INSURANCE_COST_HEALTH,
                COVERAGE_AMOUNT_HEALTH,
                currencyName,
                name,
                countryName,
                ContractStatus.UNPAID_CONTRACT,
                insuranceType));

        insuranceServiceJapan = new InsuranceServiceJapan(property, mockarchive, numberContractGenerator,
                mockinsuranceCalcJapanServiceJapan);

        InsuranceContract expected = new InsuranceContract(number, INSURANCE_COST_HEALTH,
                COVERAGE_AMOUNT_HEALTH, currencyName, name, countryName,
                contractStatus, insuranceType);

        InsuranceContract result = insuranceServiceJapan.insurancePay(number);
        System.out.println(expected);
        System.out.println(result);
        Assertions.assertEquals(expected, result);
    }


}
