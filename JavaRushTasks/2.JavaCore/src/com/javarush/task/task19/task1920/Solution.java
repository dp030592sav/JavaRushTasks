package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    public static boolean equalsDouble(double a, double b){
        return String.valueOf(a).equals(String.valueOf(b));
    }

    public static void main(String[] args) throws IOException {
        Map<String, Double> map = new TreeMap<>();

        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
        while (fileReader.ready()) {
            String values = fileReader.readLine();
            String key = values.split(" ")[0];
            Double value = Double.parseDouble(values.split(" ")[1]);
            map.put(key, map.containsKey(key) ? map.get(key) + value : value);
        }
        fileReader.close();

        List<Double> list = new ArrayList(map.values());
        Collections.sort(list, Collections.reverseOrder());


        Double max = list.get(0);
        for (Map.Entry<String, Double> i : map.entrySet()) {
            if (equalsDouble(i.getValue(),max))
                System.out.println(i.getKey());
        }
    }
}
