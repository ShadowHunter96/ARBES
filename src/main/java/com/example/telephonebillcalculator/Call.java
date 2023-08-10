package com.example.telephonebillcalculator;

/**
 * Created by User: Vu
 * Date: 10.08.2023
 * Time: 16:13
 */


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


    public class Call {
        private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        private final String phoneNumber;
        private final Date start;
        private final Date end;


        public Call(String phoneNumber, String start, String end) {
            this.phoneNumber = phoneNumber;
            try {
                this.start = DATE_FORMAT.parse(start);
                this.end = DATE_FORMAT.parse(end);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid date formatting please reconsider");
            }
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public Date getStart() {
            return start;
        }

        public Date getEnd() {
            return end;
        }
    }

