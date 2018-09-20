package com.javarush.task.task33.task3310;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Helper {
    // генерирует случайную строку
    public static String generateRandomString() {
        return new BigInteger(130, new SecureRandom()).toString(36);
    }

    // выводит переданный текст в консоль
    public static void printMessage(String message) {
        System.out.println(message);
    }
}
