package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String res = "";
        while (true) {
            try {
                res = bis.readLine();
                break;

            } catch (IOException e) {
                writeMessage("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            }
        }

        if (res.equalsIgnoreCase("exit"))
            throw new InterruptOperationException();

        return res.toUpperCase();
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String currencyCode;

        writeMessage("Введите пожалуйста код валюты");
        while (true) {
            currencyCode = readString();
            if (currencyCode.length() == 3)
                break;
            else
                writeMessage("Произошла ошибка при попытке ввода кода валюты. Попробуйте еще раз.");
        }

        return currencyCode;
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] result;

        writeMessage("Введите пожалуйста номинал валют и их количество, через пробел");
        while (true) {
            try {
                result = readString().split(" ");
                if (Integer.parseInt(result[0]) >= 0 && Integer.parseInt(result[1]) >= 0)
                    break;
            } catch (Exception e) {
                writeMessage("Произошла ошибка при попытке ввода номинала валюты и их количества. Попробуйте еще раз.");
            }
        }

        return result;
    }

    public static Operation askOperation() throws InterruptOperationException {
        Operation result;

        writeMessage("Введите пожалуйста тип операции:\n " +
                "1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT");
        while (true) {
            try {
                int operationIndex = Integer.parseInt(readString());
                result = Operation.getAllowableOperationByOrdinal(operationIndex);
                break;
            } catch (Exception e) {
                writeMessage("Произошла ошибка при попытке ввода номинала валюты и их количества. Попробуйте еще раз.");
            }
        }

        return result;
    }

    public static int askAmountForWithdraw() throws InterruptOperationException {
        int result;

        writeMessage("Введите пожалуйста сумму для снятия");
        while (true) {
            try {
                result = Integer.parseInt(readString());
                break;
            } catch (NumberFormatException e) {
                writeMessage("Произошла ошибка при попытке ввода сумму для снятия. Попробуйте еще раз.");
            }
        }

        return result;
    }
}
