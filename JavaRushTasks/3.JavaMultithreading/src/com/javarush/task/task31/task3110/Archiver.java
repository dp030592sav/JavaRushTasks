package com.javarush.task.task31.task3110;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class Archiver {
    public static void main(String[] args) throws Exception {

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Введите полный путь до архива:");
            String pathTo = reader.readLine();
            ZipFileManager zipFileManager = new ZipFileManager(Paths.get(pathTo));
            System.out.println("Введите полный путь до файла:");
            String pathFrom = reader.readLine();
            zipFileManager.createZip(Paths.get(pathFrom));
        }
    }
}
