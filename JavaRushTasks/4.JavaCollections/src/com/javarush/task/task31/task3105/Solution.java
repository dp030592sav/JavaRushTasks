package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fullFileName = args[0];
        String fullZipName = args[1];

        Map<String, ByteArrayOutputStream> tempHashMap = new HashMap();

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(fullZipName))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                byte[] bytes = new byte[1024];
                int count = 0;
                while ((count = zipInputStream.read(bytes)) != -1)
                    byteArrayOutputStream.write(bytes, 0, count);

                tempHashMap.put(entry.getName(), byteArrayOutputStream);
            }
        }

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(fullZipName))) {
            File file = new File(fullFileName);
            zipOutputStream.putNextEntry(new ZipEntry("new/" + file.getName()));
            Files.copy(file.toPath(), zipOutputStream);
            zipOutputStream.closeEntry();

            for (Map.Entry<String, ByteArrayOutputStream> entry : tempHashMap.entrySet()) {
                if(entry.getKey().endsWith("new/" + file.getName())) continue;
                zipOutputStream.putNextEntry(new ZipEntry(entry.getKey()));
                zipOutputStream.write(entry.getValue().toByteArray());
            }
        }
    }
}
