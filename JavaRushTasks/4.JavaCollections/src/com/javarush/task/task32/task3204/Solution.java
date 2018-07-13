package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        String LOWER = "abcdefghijklmnopqrstuvwxyz";
        String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String DIGITS = "0123456789";

        Random random = new Random(System.nanoTime());
        char[] chars = new char[8];

        chars[0] = (char)LOWER.getBytes()[random.nextInt(LOWER.length())];
        chars[1] = (char)UPPER.getBytes()[random.nextInt(UPPER.length())];
        chars[2] = (char)DIGITS.getBytes()[random.nextInt(DIGITS.length())];

        for (int i = 3; i < 8; i++) {
            switch (random.nextInt(3)){
                case 0:
                    chars[i] = (char)LOWER.getBytes()[random.nextInt(LOWER.length())];
                    break;
                case 1:
                    chars[i] = (char)UPPER.getBytes()[random.nextInt(UPPER.length())];
                    break;
                case 2:
                    chars[i] = (char)DIGITS.getBytes()[random.nextInt(DIGITS.length())];
                    break;
            }
        }

        StringBuilder stringBuilder = new StringBuilder(chars.length);
        for (char c: chars) {
            double rnd = Math.random();
            if (rnd < 0.34)
                stringBuilder.append(c);
            else if (rnd < 0.67)
                stringBuilder.insert(stringBuilder.length() / 2, c);
            else
                stringBuilder.insert(0, c);
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(stringBuilder.toString().getBytes());
        return byteArrayOutputStream;
    }
}