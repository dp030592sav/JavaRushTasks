package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];

        Map<String, Double> map = new TreeMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while (reader.ready()) {
            String[] mas = reader.readLine().split(" ");
            String key = mas[0];
            Double value = Double.parseDouble(mas[1]);
            map.put(key, map.containsKey(key) ? map.get(key) + value : value);
        }
        reader.close();

        for (Map.Entry<String, Double> i : map.entrySet()) {
            System.out.println(i.getKey() + " " + i.getValue());
        }
    }
}
