package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;

class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        do {
            try {
                ConsoleHelper.writeMessage("Введите пожалуйста сумму для снятия");
                int countMoney = Integer.parseInt(ConsoleHelper.readString());
                if (currencyManipulator.isAmountAvailable(countMoney)) {
                    Map<Integer, Integer> moneyOut = currencyManipulator.withdrawAmount(countMoney);
                    for (Map.Entry<Integer, Integer> entry : moneyOut.entrySet()) {
                        ConsoleHelper.writeMessage(String.format("\t%s - %s", entry.getKey(), entry.getValue()));
                    }
                    break;
                } else
                    ConsoleHelper.writeMessage("У Вас недостаточно денег");
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при попытке ввода сумму для снятия. Попробуйте еще раз.");
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage("Cумму невозможно оброботать");
            }
        } while (true);
    }
}
