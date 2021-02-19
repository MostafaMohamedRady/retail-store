package com.billing.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class CommonUtil {

    private CommonUtil() {
    }

    public static int calculatePeriod(Date registrationDate) {
            if (registrationDate != null) {
                LocalDate old=registrationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate now=new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                return Period.between(old, now).getYears();
            } else {
                return 0;
            }
        }

}
