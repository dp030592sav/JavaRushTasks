package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new ConcurrentSkipListMap<>(String.CASE_INSENSITIVE_ORDER);

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        if(map.containsKey(currencyCode))
            return map.get(currencyCode);

        CurrencyManipulator currencyManipulator = new CurrencyManipulator(currencyCode);
        map.put(currencyCode, currencyManipulator);
        return currencyManipulator;
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        return map.values();
    }
}
