package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;
import com.javarush.task.task31.task3110.exception.WrongZipFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class Archiver {

    private static Operation operation;

    public static void main(String[] args) throws Exception {
        do {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (WrongZipFileException e) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }
        } while (operation != Operation.EXIT );

//        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
//            System.out.println("Введите полный путь до архива:");
//            String pathTo = reader.readLine();
//            ZipFileManager zipFileManager = new ZipFileManager(Paths.get(pathTo));
//            System.out.println("Введите полный путь до файла:");
//            String pathFrom = reader.readLine();
//            zipFileManager.createZip(Paths.get(pathFrom));
//            new ExitCommand().execute();
//        }
    }

    public static Operation askOperation() throws IOException {
        String outForUsser = "Выберите операцию: \n";

        for (Operation i : Operation.values())
            outForUsser += String.format("%s - %s\n", i.ordinal(), i.comment);

        ConsoleHelper.writeMessage(outForUsser);
        return Operation.values()[ConsoleHelper.readInt()];
    }
}
