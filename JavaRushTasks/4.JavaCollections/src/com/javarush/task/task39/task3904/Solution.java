package com.javarush.task.task39.task3904;

import java.util.HashMap;

/*
Лестница
*/
public class Solution {
    private static int n = 7;
    static HashMap<Integer, Long> map = new HashMap<>();
    static {
        map.put(0, 1L);
        map.put(1, 1L);
        map.put(2, 2L);
        map.put(3, 4L);
    }

    public static void main(String[] args) {
        System.out.println("Number of possible runups for " + n + " stairs is: " + countPossibleRunups(n));
    }

    public static long countPossibleRunups(int n) {
        if(n < 0)
            return 0;

        if(n > 3)
            for (int i = 4; i <= n; i++) {
                map.put(i, map.get(i -1)
                        +  map.get(i -2)
                        +  map.get(i -3));
            }

        return map.get(n);
    }
}

