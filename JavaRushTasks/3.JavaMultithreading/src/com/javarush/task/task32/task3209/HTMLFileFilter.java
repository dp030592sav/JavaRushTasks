package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        String name = f.getName();
        String type = name.substring(name.lastIndexOf("."));
        return f.isDirectory() || type.equalsIgnoreCase(".html") || type.equalsIgnoreCase(".htm");
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
