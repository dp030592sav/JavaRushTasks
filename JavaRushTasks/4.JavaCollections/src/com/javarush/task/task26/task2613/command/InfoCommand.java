package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res
            = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.info_en");

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> currencyManipulators =  CurrencyManipulatorFactory.getAllCurrencyManipulators();

        if(currencyManipulators.size() == 0)
            ConsoleHelper.writeMessage(res.getString("no.money"));

        for (CurrencyManipulator currencyManipulator : currencyManipulators) {
            if (currencyManipulator.hasMoney())
                ConsoleHelper.writeMessage(String.format("%s - %s", currencyManipulator.getCurrencyCode(), currencyManipulator.getTotalAmount()));
            else
                ConsoleHelper.writeMessage(res.getString("no.money"));
        }
    }
}
