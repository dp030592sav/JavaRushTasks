package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".common_en");
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String s) {
        System.out.println(s);
    }

    public static void printAvailableOperation() {
        writeMessage( res.getString("operation.INFO") + ": 1;\n" +
                res.getString("operation.DEPOSIT") + ": 2;\n" +
                res.getString("operation.WITHDRAW") + ": 3;\n" +
                res.getString("operation.EXIT") + ": 4\n");
    }

    public static Operation askOperation() throws InterruptOperationException{
        while (true) {
            writeMessage(res.getString("choose.operation"));
            String operation = readString();
            if (operation.equalsIgnoreCase("help")) {
                printAvailableOperation();
                operation = readString();
            }
            try {
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(operation));
            } catch (IllegalArgumentException ex) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }
    public static String readString() throws InterruptOperationException {
        String result = "";
        try {
            result = bis.readLine();
            if (result.equalsIgnoreCase("EXIT")) {
                throw new InterruptOperationException();
            }
        } catch (IOException e) {
        }
        return result;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String test;
        while (true) {
            writeMessage(res.getString("choose.currency.code"));
            test = readString();
            if (test.length() == 3)
                break;
            writeMessage(res.getString("invalid.data"));
        }
        test = test.toUpperCase();
        return test;
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] array;
        while (true) {
            ConsoleHelper.writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            String s = readString();
            array = s.split(" ");
            int k;
            int l;
            try {
                k = Integer.parseInt(array[0]);
                l = Integer.parseInt(array[1]);
            } catch (Exception e) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            if (k <= 0 || l <= 0 || array.length > 2) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            break;
        }
        return array;
    }

    public static void printExitMessage() {
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }
}