package com.javacademy.insurance;

import com.javacademy.insurance.insurence_objects.InsuranceType;
import com.javacademy.insurance.service.japan.InsuranceCalcJapanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

@ActiveProfiles("japan")
@SpringBootTest
public class InsuranceCalcJapanServiceTest {
    private static final BigDecimal EXPECTED_ROBBERY = BigDecimal.valueOf(20000);
    private static final BigDecimal COVERAGE_AMOUNT_ROBBERY = BigDecimal.valueOf(1000000);
    private static final BigDecimal EXPECTED_HEALTH = BigDecimal.valueOf(162000);
    private static final BigDecimal COVERAGE_AMOUNT_HEALTH = BigDecimal.valueOf(10_000_000);
    @Autowired
    InsuranceCalcJapanService insuranceCalcJapanService;

    /**
     * Ситуация №1: Рассчитать стоимость страховки при грабеже, сумма покрытия 1 000 000. Ожидаемая стоимость: 20 000.
     */
    @Test
    public void insuranceCostRubberyTest() {

        BigDecimal expected = EXPECTED_ROBBERY.stripTrailingZeros();
        BigDecimal result = insuranceCalcJapanService.insuranceCost(COVERAGE_AMOUNT_ROBBERY,
                InsuranceType.ROBBERY_INSURANCE).stripTrailingZeros();
        Assertions.assertEquals(expected, result);
    }

    /**
     * Ситуация №2: Рассчитать стоимость страховки при мед страховке, сумма покрытия 10 000 000.
     * Ожидаемая стоимость: 162 000.
     */
    @Test
    public void insuranceCostHealthTest() {
        BigDecimal expected = EXPECTED_HEALTH.stripTrailingZeros();
        BigDecimal result = insuranceCalcJapanService.insuranceCost(COVERAGE_AMOUNT_HEALTH,
                InsuranceType.HEALTH_INSURANCE).stripTrailingZeros();
        Assertions.assertEquals(expected, result);
    }


}
