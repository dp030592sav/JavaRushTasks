package com.javarush.task.task33.task3310;

// оброботчик исключений
public class ExceptionHandler {
    public static void log(Exception e) {
        Helper.printMessage(e.getMessage());
    }
}