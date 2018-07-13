package com.javarush.task.task32.task3202;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter stringWriter = new StringWriter();
        if(is == null)
            return stringWriter;

        byte[] bytes = new byte[is.available()];
        while (is.available() != 0){
            is.read(bytes);
        }
        stringWriter.write(new String(bytes));

        return stringWriter;
    }
}