package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
//        testStrategy(new FileStorageStrategy(), 10000);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
        testStrategy(new HashBiMapStorageStrategy(), 10000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);
    }

    // метод должен для переданного множества строк возвращать множество идентификаторов
    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> result = new HashSet<>();

        for (String i : strings)
            result.add(shortener.getId(i));

        return result;
    }

    // метод будет возвращать множество строк, которое соответствует переданному множеству идентификаторов
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> result = new HashSet<>();

        for (Long i : keys)
            result.add(shortener.getString(i));

        return result;
    }

    // метод будет тестировать работу переданной стратегии на определенном количестве элементов elementsNumber
    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        // выводить имя класса стратегии. Имя не должно включать имя пакета
        Helper.printMessage(strategy.getClass().getSimpleName());

        // генерировать тестовое множество строк
        HashSet<String> strings = new HashSet<>();

        for (long i = 0; i < elementsNumber; i++)
            strings.add( Helper.generateRandomString());

        // создавать объект типа Shortener, используя переданную стратегию
        Shortener shortener = new Shortener(strategy);

        // замерять и выводить время необходимое для отработки метода getIds для заданной стратегии
        Date startTime = new Date();
        Set<Long> keys = getIds(shortener, strings);
        Date endTime = new Date();

        Long delta = endTime.getTime() - startTime.getTime();
        Helper.printMessage(delta.toString());

        // замерять и выводить время необходимое для отработки метода getStrings для заданной стратегии
        startTime = new Date();
        Set<String> values = getStrings(shortener, keys);
        endTime = new Date();

        delta = endTime.getTime() - startTime.getTime();
        Helper.printMessage(delta.toString());

        // сравнивать одинаковое ли содержимое множества строк, которое было сгенерировано и множества,
        // которое было возвращено методом getStrings.
        if(strings.equals(values))
            Helper.printMessage("Тест пройден.");
        else
            Helper.printMessage("Тест не пройден.");
    }
}
