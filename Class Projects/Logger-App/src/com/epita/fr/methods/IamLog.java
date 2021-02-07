package com.epita.fr.methods;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class IamLog {
    public String log(String message){
        //Do something here
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/mm/dd - HH:mm:ss.SSSS");
        Date date = new Date();
        LocalDateTime localDateTime = LocalDateTime.now();
        String theCurrentDateTime = simpleDateFormat.format(date);
//        String theCurrentDateTime = simpleDateFormat.format(localDateTime);
        System.out.println(theCurrentDateTime + " " + message);
//        System.out.println(localDateTime.toInstant()atZone().toLocalDate() + " " + message);
        return theCurrentDateTime;
    }
}
