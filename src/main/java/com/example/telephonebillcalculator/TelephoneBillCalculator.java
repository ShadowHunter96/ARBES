package com.example.telephonebillcalculator;

import java.math.BigDecimal;

/**
 * Created by User: Vu
 * Date: 10.08.2023
 * Time: 16:05
 */
public interface TelephoneBillCalculator {
    BigDecimal calculate(String phoneLog);
}
