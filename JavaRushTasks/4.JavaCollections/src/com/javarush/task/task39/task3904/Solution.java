package com.javarush.task.task39.task3904;

import java.util.Arrays;

/* 
Лестница
*/
public class Solution {
    private static int n = 70;
    public static void main(String[] args) {
        System.out.println("Number of possible runups for " + n + " stairs is: " + countPossibleRunups(n));
    }

    public static long countPossibleRunups(int n) {
        int result = 0;

        try {
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        if(n < 0)
            return 0;
        if(n == 1)
            return 1;

        return result;
    }
}

