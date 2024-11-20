package com.javacademy.insurance;

import com.javacademy.insurance.Enum.ContractStatus;
import com.javacademy.insurance.Enum.TypeInsurance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class InsuranceContract {
    // номер договора
    @Getter
    private String number;
    // сумма страховки
    private BigDecimal insuranceCost;
    // сумма покрытия
    private BigDecimal coverageAamount;
    // валюта
    private String currency;
    // ФИО клиента
    private String name;
    // страна
    private String country;

    // статус договора
    @Setter
    private ContractStatus contractStatus;
    // тип договора
    private TypeInsurance typeInsurance;


}
