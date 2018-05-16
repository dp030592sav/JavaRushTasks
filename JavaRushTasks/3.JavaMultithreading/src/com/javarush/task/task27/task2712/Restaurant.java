package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) {
        // создание и регистрация поворов
        Cook cook1 = new Cook("Amigo");
        Cook cook2 = new Cook("MagicCook");
        StatisticManager.getInstance().register(cook1);
        StatisticManager.getInstance().register(cook2);

        // создание планшетов и установка зависимостей Observer-Observable
        List<Tablet> tablets = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.addObserver(cook1);
            tablet.addObserver(cook2);
            tablets.add(tablet);
        }

        // создание официанта и установка зависимостей Observer-Observable
        Waiter waiter = new Waiter();
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);

        //  Создай и запуск трэда на основе объекта RandomOrderGeneratorTask
        Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();

        // вывод статистики
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();

    }
}
