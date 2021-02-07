package com.epita.fr.methods;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class TimeZoneDates {

    public void localDates(){
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(TimeZone.getDefault().toZoneId()).toLocalDate();
        System.out.println("Converted Date: " + localDate);
        LocalDate now = LocalDate.now();
    }

    public void moreLocalDates(){
        LocalDate now = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY/mm/dd");
        String nowString = now.format(dateTimeFormatter);
        System.out.println(nowString);
    }
}
