package com.javarush.task.task33.task3310.strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            this.path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
            path.toFile().deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // должен возвращать размер файла на который указывает path
    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // сериализовывать переданный entry в файл. Учти, каждый entry может содержать еще один entry
    public void putEntry(Entry entry) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(path))) {
            objectOutputStream.writeObject(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // должен забирать entry из файла. Если файл имеет нулевой размер, вернуть null
    public Entry getEntry() {
        Entry entry = null;

        if (getFileSize() <= 0)
            return null;

        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path))) {
            try {
                entry = (Entry) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entry;
    }

    // удалять файл на который указывает path
    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
