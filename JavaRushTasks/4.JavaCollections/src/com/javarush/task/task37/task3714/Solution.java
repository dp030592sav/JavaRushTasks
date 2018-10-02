package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    enum Numbers {
        I(1),
        V (5),
        X (10),
        L (50),
        C (100),
        D (500),
        M (1000);

        private int numVal;

        Numbers(int numVal) {
            this.numVal = numVal;
        }

        public int getNumVal() {
            return numVal;
        }
    }

    public static int romanToInteger(String s) {
        char[] chars = s.toUpperCase().toCharArray();
        int sum = 0;
        int prevNumber = 0;

        for (int i = chars.length-1; i >= 0; i--) {
            int number = Numbers.valueOf(String.valueOf(chars[i])).getNumVal();
            if (number >= prevNumber)
                sum += number;
            else
                sum -= number;

            prevNumber = number;
        }

        return sum;
    }
}
