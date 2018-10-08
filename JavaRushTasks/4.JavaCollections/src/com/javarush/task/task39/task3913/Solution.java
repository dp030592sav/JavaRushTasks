package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("C:\\Users\\Sloboda Alex\\Desktop\\Test1"));
        Date date = new Date();
        date.setYear(2013 - 1900);
        date.setMonth(7);
        date.setDate(28);

        Date date1 = new Date();
        date1.setYear(2013 - 1900);
        date1.setMonth(7);
        date1.setDate(31);

//        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getUniqueIPs(date, date1));
    }
}