package com.javarush.task.task31.task3113;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private int countDirectories;
    private int countFiles;
    private int sizeFiles;

    public int getCountDirectories() {
        return countDirectories;
    }

    public int getCountFiles() {
        return countFiles;
    }

    public int getSizeFiles() {
        return sizeFiles;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        countDirectories++;

        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        countFiles++;
        byte[] content = Files.readAllBytes(path);
        sizeFiles += content.length;

        return super.visitFile(path, attrs);
    }
}
