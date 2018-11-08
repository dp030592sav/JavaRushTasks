package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class DepositCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = ConsoleHelper.askCurrencyCode();
        String[] validTwoDigits = ConsoleHelper.getValidTwoDigits(currencyCode);

        CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode)
                .addAmount(Integer.parseInt(validTwoDigits[0]), Integer.parseInt(validTwoDigits[1]));
    }
}
