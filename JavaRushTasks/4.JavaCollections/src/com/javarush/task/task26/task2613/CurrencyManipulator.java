package com.javarush.task.task26.task2613;


import java.util.HashMap;
import java.util.Map;

/**
 * который будет хранить всю информацию про выбранную валюту
 */
public class CurrencyManipulator {
    // код валюты, например, USD. Состоит из трех букв
    private String currencyCode;
    // это Map<номинал, количество>
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination))
            denominations.put(denomination, denominations.get(denomination) + count);
        else
            denominations.put(denomination, count);
    }

    public int getTotalAmount() {
        int totalAmount = 0;

        for (Map.Entry<Integer, Integer> entry : denominations.entrySet())
            totalAmount += entry.getKey() * entry.getValue();

        return totalAmount;
    }

    public boolean hasMoney(){
        return getTotalAmount() != 0;
    }
}
