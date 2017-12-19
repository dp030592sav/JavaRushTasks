package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));

        while (reader.ready()){
            String[] values = reader.readLine().split(" ");

            String name = "";
            for (int i = 0; i < values.length - 3; i++) {
                name += values[i] + " ";
            }
            name = name.trim();

            Date date = new Date();
            date.setDate(Integer.parseInt(values[values.length - 3]));
            date.setMonth(Integer.parseInt(values[values.length - 2]) - 1);
            date.setYear(Integer.parseInt(values[values.length - 1]) - 1900);

            PEOPLE.add(new Person(name, date));
        }

        reader.close();
    }
}
