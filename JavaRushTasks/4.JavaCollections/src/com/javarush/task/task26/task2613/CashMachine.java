package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

public class CashMachine {
    public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName() + ".resources.";

    public static void main(String[] args) {
        ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + ".common_en");
        Locale.setDefault(Locale.ENGLISH);
        Operation op;
        try {
            CommandExecutor.execute(Operation.LOGIN);
            ConsoleHelper.writeMessage(res.getString("write.help") + "\n");
            do {
                op = ConsoleHelper.askOperation();
                CommandExecutor.execute(op);
            } while (op != Operation.EXIT);
        } catch (InterruptOperationException ignored) {}
        ConsoleHelper.printExitMessage();
    }
}