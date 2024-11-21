package com.javacademy.insurance;

import com.javacademy.insurance.insurence_objects.InsuranceType;
import com.javacademy.insurance.service.brazil.InsuranceCalcBrazilService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

@ActiveProfiles("brazil")
@SpringBootTest
public class InsuranceCalcBrazilServiceTest {
    private static final BigDecimal EXPECTED_ROBBERY = BigDecimal.valueOf(2800);
    private static final BigDecimal COVERAGE_AMOUNT_ROBBERY = BigDecimal.valueOf(50000);
    private static final BigDecimal EXPECTED_HEALTH = BigDecimal.valueOf(6800);
    private static final BigDecimal COVERAGE_AMOUNT_HEALTH = BigDecimal.valueOf(200_000);
    @Autowired
    InsuranceCalcBrazilService insuranceCalcBrazilService;

     /**
     * Ситуация №1: Рассчитать стоимость страховки при грабеже, сумма покрытия 50 000. Ожидаемая стоимость: 2800.
     */
    @Test
    public void insuranceCostRubberyTest() {

        BigDecimal expected = EXPECTED_ROBBERY.stripTrailingZeros();
        BigDecimal result = insuranceCalcBrazilService.insuranceCost(COVERAGE_AMOUNT_ROBBERY,
                InsuranceType.ROBBERY_INSURANCE).stripTrailingZeros();
        Assertions.assertEquals(expected, result);
    }

    /**
     * Ситуация №2: Рассчитать стоимость страховки при мед страховке, сумма покрытия 200 000. Ожидаемая стоимость: 6800
     */
    @Test
    public void insuranceCostHealthTest() {

        BigDecimal expected = EXPECTED_HEALTH.stripTrailingZeros();
        BigDecimal result = insuranceCalcBrazilService.insuranceCost(COVERAGE_AMOUNT_HEALTH,
                InsuranceType.HEALTH_INSURANCE).stripTrailingZeros();
        Assertions.assertEquals(expected, result);
    }


}
