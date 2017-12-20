package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
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
        while (fileReader.ready()){
            text += (char) fileReader.read();
        }
        fileReader.close();

        String[] words = text.replaceAll(System.getProperty("line.separator"), " ").split(" ");

        FileWriter fileWriter = new FileWriter(fileName2);
        for (String i : words) {
            if(!i.matches("^\\D*$"))
                fileWriter.write(i + " ");
        }
        fileWriter.close();
    }
}
