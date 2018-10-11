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
//        System.out.println(logParser.execute("get ip for user = \"Vasya\""));
//        System.out.println(logParser.execute("get user"));
//        System.out.println("------------------------------");
//        System.out.println(logParser.execute("get event for date = \"28.08.2013 10:11:12\""));
        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"28.08.2013 3:04:51\" and \"28.08.2013 10:12:12\""));

    }
}