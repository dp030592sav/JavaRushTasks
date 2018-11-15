package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {
    }


    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if (map.containsKey(currencyCode)) {
            return map.get(currencyCode);
        } else {
            CurrencyManipulator cur = new CurrencyManipulator(currencyCode);
            map.put(currencyCode, cur);
            return cur;
        }
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return map.values();
    }
}
