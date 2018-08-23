package com.javarush.task.task35.task3507;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ModuleLoader extends ClassLoader {

    public Class load(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(String.valueOf(path)));

        return defineClass(null, bytes, 0, bytes.length);
    }
}
