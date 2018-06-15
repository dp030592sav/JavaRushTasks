package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Arrays.sort(args, 1, args.length);
        Vector<InputStream> vector = new Vector<>();

        for (int i = 1; i < args.length; i++)
            vector.add(new FileInputStream(args[i]));


        try (ZipInputStream zipInputStream = new ZipInputStream(new SequenceInputStream(vector.elements()));
             FileOutputStream outputStream = new FileOutputStream(args[0]))
        {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                byte[] bytes = new byte[1024];
                int count = zipInputStream.read(bytes);
                outputStream.write(bytes , 0 , count);
            }
        }
    }
}
