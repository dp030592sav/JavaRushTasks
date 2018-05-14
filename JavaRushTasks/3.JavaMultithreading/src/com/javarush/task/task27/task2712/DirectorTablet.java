package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {
    // сумма заработанная на рекламе, сгруппированная по дням
    public void printAdvertisementProfit() {
        Map<Date, Double> videosSelected = StatisticManager.getInstance().getProfitReport();

        Double total = 0.0;
        for (Map.Entry<Date, Double> i : videosSelected.entrySet()) {
            total += i.getValue();
            System.out.println(String.format("%s - %s", new SimpleDateFormat("dd-MMM-yyyy").format(i.getKey()), i.getValue() / 100));
        }
        System.out.println(String.format("Total - %s", total / 100));
    }

    // загрузка (рабочее время) повара, сгруппированная по дням
    public void printCookWorkloading() {
        Map<Date, Map<String, Integer>> cooksReport = StatisticManager.getInstance().getCooksReport();

        for (Map.Entry<Date, Map<String, Integer>> i : cooksReport.entrySet()) {
            ConsoleHelper.writeMessage(new SimpleDateFormat("dd-MMM-yyyy").format(i.getKey()));

            for (Map.Entry<String, Integer> j : i.getValue().entrySet()) {
                ConsoleHelper.writeMessage(String.format("%s - %d min", j.getKey(), j.getValue()));
            }
            ConsoleHelper.writeMessage("");
        }
    }

    // список активных роликов и оставшееся количество показов по каждому
    public void printActiveVideoSet() {
    }

    // список неактивных роликов (с оставшемся количеством показов равным нулю)
    public void printArchivedVideoSet() {
    }
}
