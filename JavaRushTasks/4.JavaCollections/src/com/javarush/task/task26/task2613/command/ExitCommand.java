package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class ExitCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Вы действительно хотите выйти - y:да n:нет");
        String str = ConsoleHelper.readString();

        if(str.equalsIgnoreCase("y"))
            ConsoleHelper.writeMessage("До новых встреч");
    }
}
