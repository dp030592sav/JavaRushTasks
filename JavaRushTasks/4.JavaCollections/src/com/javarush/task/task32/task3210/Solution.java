package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];
        long number = Long.parseLong(args[1]);
        String text = args[2];

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw")) {
            randomAccessFile.seek(number);
            byte[] buffer = new byte[text.length()];
            randomAccessFile.read(buffer, 0, text.length());
            String readText = new String(buffer, StandardCharsets.UTF_8);

            randomAccessFile.seek(randomAccessFile.length());
            if (readText.equals(text))
                randomAccessFile.write("true".getBytes());
            else
                randomAccessFile.write("false".getBytes());
        }
    }
}
