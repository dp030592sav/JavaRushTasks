package com.javarush.task.task31.task3111;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;
    private List<Path> foundFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        boolean needAdd = true;
        byte[] content = Files.readAllBytes(path); // размер файла: content.length

        if (partOfName != null && !path.toString().contains(partOfName))
            needAdd = false;
        if (partOfContent != null && new String(Files.readAllBytes(path)).contains(partOfContent))
            needAdd = false;
        if (maxSize != 0 && content.length > maxSize)
            needAdd = false;
        if (minSize != 0 && content.length < minSize)
            needAdd = false;

        if(needAdd)
            foundFiles.add(path);

        return super.visitFile(path, attrs);
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }
}
