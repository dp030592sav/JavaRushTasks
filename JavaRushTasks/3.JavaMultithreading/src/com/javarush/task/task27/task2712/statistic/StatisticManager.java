package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticManager {
    private static volatile StatisticManager instance = null;
    private StatisticStorage statisticStorage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet<>();

    private StatisticManager() {
    }

    public static StatisticManager getInstance() {
        if (instance == null)
            instance = new StatisticManager();
        return instance;
    }

    public StatisticStorage getStatisticStorage() {
        return statisticStorage;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }

    public List<VideoSelectedEventDataRow> getProfit() {
        List<VideoSelectedEventDataRow> res = new ArrayList<>();

        for (Map.Entry<EventType, List<EventDataRow>> i : getStatisticStorage().storage.entrySet()) {
            if (i.getKey() == EventType.SELECTED_VIDEOS)
                for (EventDataRow j : i.getValue()) {
                    res.add((VideoSelectedEventDataRow) j);
                }
        }

        return res;
    }

    public List<CookedOrderEventDataRow> getCooksTime() {
        List<Date> res2 = new ArrayList<>();
        List<CookedOrderEventDataRow> res = new ArrayList<>();

        for (Map.Entry<EventType, List<EventDataRow>> i : getStatisticStorage().storage.entrySet()) {
            if (i.getKey() == EventType.COOKED_ORDER)
                for (EventDataRow j : i.getValue()) {
                    res.add((CookedOrderEventDataRow) j);
                }
        }

        return res;
    }


    // хранилище
    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            for (EventType eventType : EventType.values()) {
                storage.put(eventType, new ArrayList<>());
            }
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }
    }
}
