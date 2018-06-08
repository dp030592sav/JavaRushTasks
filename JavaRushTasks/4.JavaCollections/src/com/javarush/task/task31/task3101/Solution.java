package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get(args[0]);
        File fileOut = new File(args[1]);

        List<File> files = new ArrayList<>();

        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toFile().length() <= 50)
                    files.add(file.toFile());

                return FileVisitResult.CONTINUE;
            }
        });

        Collections.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        File renameFile = new File(fileOut.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(fileOut, renameFile);

        FileOutputStream outputStream = new FileOutputStream(renameFile);

        for (File file : files) {
            if (file.getName().equals("allFilesContent.txt")) continue;
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            outputStream.write(bytes);
            outputStream.write(System.getProperty("line.separator").getBytes());
            inputStream.close();
        }

        outputStream.close();
    }
}