package com.kd.personal.mysql.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

// String to LocalDate in java 8
public class LocalDateExample5   
{  
    public static void main(String ar[])   
    {  
        // Example 1  
        String dInStr = "2011-09-01";  
        LocalDate d1 = LocalDate.parse(dInStr);  
        System.out.println("String to LocalDate : " + d1);  
        // Example 2  
        String dInStr2 = "2015-11-20";  
        LocalDate d2 = LocalDate.parse(dInStr2);  
        System.out.println("String to LocalDate : " + d2);
//        LocalDate date = LocalDate.parse("8-Jan-2020", DateTimeFormatter.ofPattern("d-MMM-yyyy"));
//        System.out.println("d-MMM-yyyy=>"+date);
        LocalDate date = LocalDate.parse("2020/01/08", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println(date);

        Date min, max;
        
    }  
}  