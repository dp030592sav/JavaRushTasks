package com.javarush.task.task26.task2613;


import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

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

    public boolean hasMoney() {
        return getTotalAmount() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> tempMap = new HashMap<>();
        tempMap.putAll(denominations);


        Map<Integer, Integer> result = new TreeMap(Collections.reverseOrder());
        int expectedAmountCopy = expectedAmount;

        for (Map.Entry<Integer, Integer> entry : tempMap.entrySet()) {
            int currencyValue = entry.getKey();
            int currencyCount = entry.getValue();

            for (int i = 0; i < currencyCount; i++) {
                if (currencyValue <= expectedAmountCopy) {
                    if (result.containsKey(currencyValue))
                        result.put(currencyValue, result.get(currencyValue) + 1);
                    else
                        result.put(currencyValue, 1);

                    expectedAmountCopy -= currencyValue;
                } else
                    break;
            }
        }

        if (expectedAmountCopy != 0)
            throw new NotEnoughMoneyException();

        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            int key = entry.getKey();
            if (tempMap.get(key) != 1)
                tempMap.put(key, tempMap.get(key) - 1);
            else
                tempMap.remove(key);
        }

        denominations.clear();
        denominations.putAll(tempMap);

        return result;
    }
}
