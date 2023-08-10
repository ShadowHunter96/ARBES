package com.example.telephonebillcalculator;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by User: Vu
 * Date: 10.08.2023
 * Time: 16:12
 */
public class TelephoneBillCalculatorImpl implements TelephoneBillCalculator{

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private static final BigDecimal NORMAL_RATE = BigDecimal.valueOf(1);
    private static final BigDecimal REDUCED_RATE = BigDecimal.valueOf(0.50);
    private static final BigDecimal LONG_CALL_RATE = BigDecimal.valueOf(0.20);

//    getting each log
    @Override
    public BigDecimal calculate(String phoneLog) {
        String[] logs = phoneLog.split(",");
        Map<String, List<Call>> callRecords = new HashMap<>();

        for (int i = 0; i < logs.length; i+=3) {
            Call call = new Call(logs[i], logs[i+1], logs[i+2]);
            callRecords.computeIfAbsent(call.getPhoneNumber(), k -> new ArrayList<>()).add(call);
        }


        String mostCalledNumber = callRecords.entrySet().stream()
                .max(Comparator.comparingInt(e -> e.getValue().size()))
                .map(Map.Entry::getKey).orElse(null);

        BigDecimal totalCost = BigDecimal.ZERO;

        for (Map.Entry<String, List<Call>> entry : callRecords.entrySet()) {
            for (Call call : entry.getValue()) {
                if (!call.getPhoneNumber().equals(mostCalledNumber)) {
                    totalCost = totalCost.add(calculateCallCost(call));
                }
            }
        }

        return totalCost;
    }

//    calculating cost
    private BigDecimal calculateCallCost(Call call) {
        long callDurationInSeconds = (call.getEnd().getTime() - call.getStart().getTime()) / 1000;
        long minutes = (callDurationInSeconds + 59) / 60;
        BigDecimal cost = BigDecimal.ZERO;

        for (long i = 0; i < minutes; i++) {
            Date currentMinuteStart = new Date(call.getStart().getTime() + i * 60 * 1000);
            if (i < 5) {
                if (isDuringNormalRate(currentMinuteStart)) {
                    cost = cost.add(NORMAL_RATE);
                } else {
                    cost = cost.add(REDUCED_RATE);
                }
            } else {
                cost = cost.add(LONG_CALL_RATE);
            }
        }

        return cost;
    }

    // sorting by time
    private boolean isDuringNormalRate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour >= 8 && hour < 16;
    }
}
