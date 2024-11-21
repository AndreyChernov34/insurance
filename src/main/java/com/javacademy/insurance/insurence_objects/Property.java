package com.javacademy.insurance.insurence_objects;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix =  "app")
@Data
public class Property {
    private CountryName country;
    private CurrencyName currency;
    private BigDecimal healthCoefficient;
    private BigDecimal healthAmount;
    private BigDecimal robberyCoefficient;
    private BigDecimal robberyAmount;

}
