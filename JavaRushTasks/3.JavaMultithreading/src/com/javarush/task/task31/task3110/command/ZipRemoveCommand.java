package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipRemoveCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {

        ConsoleHelper.writeMessage("Удаление файла из архива.");

        ZipFileManager zipFileManager = getZipFileManager();

        ConsoleHelper.writeMessage("Введите имя файла архива для удаления:");
        Path removeFilePath = Paths.get(ConsoleHelper.readString());

        zipFileManager.removeFile(removeFilePath);

        ConsoleHelper.writeMessage("Файл из архива был удален.");
    }
}
