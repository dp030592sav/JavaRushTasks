package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {

    private ResourceBundle validCreditCards
            = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards");

    @Override
    public void execute() throws InterruptOperationException {
        long curdNumber, pinCode;

        do {
            try {
                ConsoleHelper.writeMessage("Введите пожалусто номер карты");
                curdNumber = Long.parseLong(ConsoleHelper.readString());
                ConsoleHelper.writeMessage("Введите пожалусто пин код карты");
                pinCode = Long.parseLong(ConsoleHelper.readString());

                if(validCreditCards.containsKey(curdNumber + "")
                        && validCreditCards.getString(curdNumber + "").equals(pinCode + ""))
                    break;
                else
                    ConsoleHelper.writeMessage("Вы ввели неверный номер карты или пин код, попробуйте еще раз пожалуйсто");
            } catch (NumberFormatException | InterruptOperationException e) {
                ConsoleHelper.writeMessage("Вы ввели неверный номер карты или пин код, попробуйте еще раз пожалуйсто");
            }
        } while (true);
        ConsoleHelper.writeMessage("Данные успешно прошли верификацию");
    }
}
