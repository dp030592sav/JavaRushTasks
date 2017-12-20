package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName1 = args[0];
        String fileName2 = args[1];

        FileReader fileReader = new FileReader(fileName1);
        String text = "";
        while (fileReader.ready()) {
            text += (char) fileReader.read();
        }
        fileReader.close();

        String[] words = text.replaceAll(System.getProperty("line.separator"), " ").split(" ");


        String result = "";
        for (String i : words) {
            if (i.length() > 6)
                result += i + ",";
        }
        result = result.substring(0, result.length() - 1);

        FileWriter fileWriter = new FileWriter(fileName2);
        fileWriter.write(result);
        fileWriter.close();
    }
}
