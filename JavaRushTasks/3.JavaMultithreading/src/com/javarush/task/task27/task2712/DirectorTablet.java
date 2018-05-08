package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;
import com.sun.org.apache.xpath.internal.operations.Number;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DirectorTablet {
    // сумма заработанная на рекламе, сгруппированная по дням
    public void printAdvertisementProfit() {
        List<VideoSelectedEventDataRow> videosSelected = StatisticManager.getInstance().getProfit();

        Collections.sort(videosSelected, new Comparator<VideoSelectedEventDataRow>() {
            @Override
            public int compare(VideoSelectedEventDataRow o1, VideoSelectedEventDataRow o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });

        long total = 0;
        for (VideoSelectedEventDataRow i : videosSelected) {
            total += i.getAmount();
            System.out.println(String.format("%s - %d", new SimpleDateFormat("dd-MMM-yyyy").format(i.getDate()), i.getAmount()));
        }
        System.out.println(String.format("Total - %d", total));
    }

    // загрузка (рабочее время) повара, сгруппированная по дням
    public void printCookWorkloading() {
        List<CookedOrderEventDataRow> cooksTime = StatisticManager.getInstance().getCooksTime();

        Collections.sort(cooksTime, new Comparator<CookedOrderEventDataRow>() {
            @Override
            public int compare(CookedOrderEventDataRow o1, CookedOrderEventDataRow o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });

        for ( i : cooksTime) {

        }
    }

    // список активных роликов и оставшееся количество показов по каждому
    public void printActiveVideoSet() {
    }

    // список неактивных роликов (с оставшемся количеством показов равным нулю)
    public void printArchivedVideoSet() {
    }
}
