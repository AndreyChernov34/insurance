package com.javacademy.insurance.insurence_objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
public class InsuranceContract {
    // номер договора
    @Getter
    private String number;
    // сумма страховки
    private BigDecimal insuranceCost;
    // сумма покрытия
    private BigDecimal coverageAmount;
    // валюта
    private CurrencyName currency;
    // ФИО клиента
    private String name;
    // страна
    private CountryName country;

    // статус договора
    @Setter
    private ContractStatus contractStatus;
    // тип договора
    private InsuranceType insuranceType;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof InsuranceContract)) {
            return false;
        }
        InsuranceContract that = (InsuranceContract) object;
        return Objects.equals(number, that.number)
                && Objects.equals(insuranceCost, that.insuranceCost)
                && Objects.equals(coverageAmount, that.coverageAmount)
                && currency == that.currency
                && Objects.equals(name, that.name)
                && country == that.country
                && contractStatus == that.contractStatus
                && insuranceType == that.insuranceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, insuranceCost, coverageAmount, currency, name, country, contractStatus,
                insuranceType);
    }

    @Override
    public String toString() {
        return "InsuranceContract{"
                + "contractStatus=" + contractStatus
                + ", number='" + number + '\''
                + ", insuranceCost=" + insuranceCost
                + ", coverageAmount=" + coverageAmount
                + ", currency=" + currency
                + ", name='" + name + '\''
                + ", country=" + country
                + ", insuranceType=" + insuranceType
                + '}';
    }
}
