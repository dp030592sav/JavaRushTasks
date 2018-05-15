package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

// будет предоставлять информацию из AdvertisementStorage в нужном виде
public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager instance = null;
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {
    }

    public static StatisticAdvertisementManager getInstance() {
        if (instance == null) {
            instance = new StatisticAdvertisementManager();
        }
        return instance;
    }

    public List<Advertisement> getActiveAdvertisements(){
        List<Advertisement> result = new ArrayList<>();

        for (Advertisement advertisement : advertisementStorage.list()) {
            if(advertisement.getHits() > 0)
                result.add(advertisement);
        }

        return result;
    }

    public List<Advertisement> getNoActiveAdvertisements(){
        List<Advertisement> result = new ArrayList<>();

        for (Advertisement advertisement : advertisementStorage.list()) {
            if(advertisement.getHits() <= 0)
                result.add(advertisement);
        }

        return result;
    }
}
