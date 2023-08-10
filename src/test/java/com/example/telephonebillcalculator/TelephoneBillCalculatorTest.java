package com.example.telephonebillcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by User: Vu
 * Date: 10.08.2023
 * Time: 16:18
 */
public class TelephoneBillCalculatorTest {

    private TelephoneBillCalculator calculator;

    @BeforeEach
    public void setup() {
        calculator = new TelephoneBillCalculatorImpl();
    }

    @Test
    public void testCalculate() {
        String log = "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57,420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00,420776562353,18-01-2020 09:10:01,18-01-2020 09:15:00";

        BigDecimal result = calculator.calculate(log);
        BigDecimal expectedValue = new BigDecimal("1.5");

        assertEquals(expectedValue, result);
    }
}
