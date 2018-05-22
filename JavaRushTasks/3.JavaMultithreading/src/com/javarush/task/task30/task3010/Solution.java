package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigDecimal;
import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        try {
            String data = args[0];

            for (int i = 2; i < 37; i++) {
                try {
                    new BigInteger(data, i);
                    System.out.println(i);
                    break;
                } catch (Exception e) {
                    if(i == 36)
                        System.out.println("incorrect");
                }
            }
        } catch (Exception e) {
        }
    }
}