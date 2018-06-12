package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

/*
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pathDirectory = reader.readLine();
        reader.close();

        Path path = Paths.get(pathDirectory);
        if(!Files.isDirectory(path)) {
            System.out.println(String.format("%s - не папка", path));
            return;
        }

        SearchFileVisitor visitor = new SearchFileVisitor();
        Files.walkFileTree(path, visitor);

        System.out.println(String.format("Всего папок - %d", visitor.getCountDirectories() - 1));
        System.out.println(String.format("Всего файлов - %d", visitor.getCountFiles()));
        System.out.println(String.format("Общий размер - %d", visitor.getSizeFiles()));
    }
}
