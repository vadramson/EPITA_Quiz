package com.epita.fr.methods;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SimpleDates {

    public void simpledate(){
        //Creates a date variable of type Date, the default constructor
        //initializes the date variable with the date of the date
        Date date = new Date();
        System.out.println(date);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss.SSS");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String stringResult = simpleDateFormat.format(date);
        System.out.println(stringResult);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your date !");
        String enteredDate = scanner.next();
        Date enteredDateResult = null;
        try {
            enteredDateResult = simpleDateFormat.parse(enteredDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(enteredDateResult);
    }
}
