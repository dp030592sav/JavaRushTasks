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
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if(partOfName != null && file.toString().contains(partOfName))
            foundFiles.add(file);
        else if(partOfContent != null){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file.toString())));
            List<String> list = new ArrayList<>();
            while (bufferedReader.ready()){
                list.add(bufferedReader.readLine());
            }
            bufferedReader.close();
        }
        byte[] content = Files.readAllBytes(file); // размер файла: content.length

        return super.visitFile(file, attrs);
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
