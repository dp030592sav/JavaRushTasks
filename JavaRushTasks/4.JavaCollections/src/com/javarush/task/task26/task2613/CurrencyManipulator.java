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

    public boolean hasMoney(){
        return getTotalAmount() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount){
        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> tempMap = new HashMap<>();
        tempMap.putAll(denominations);
        Map<Integer, Integer> result = new TreeMap(Collections.reverseOrder());

        for (Map.Entry<Integer, Integer> entry : tempMap.entrySet()) {
            //tasks - массив заданий
            Arrays.sort(tasks); //сортируем по убыванию стоимости
            TreeSet<Integer> time = new TreeSet<Integer>();
            for (int i = 1; i <= n; ++i) {
                time.add(i);
            }
            int ans = 0;
            for (int i = 0; i < n; ++i) {
                Integer tmp = time.floor(tasks[i].time);
                if (tmp == null) { // если нет свободного места в расписании, то в конец
                    time.remove(time.last());
                } else { //иначе можно выполнить задание, добавляем в расписание
                    time.remove(tmp);
                    ans += tasks[i].cost;
                }
            }
        }

        denominations.clear();
        denominations.putAll(tempMap);

        return result;
    }
}
