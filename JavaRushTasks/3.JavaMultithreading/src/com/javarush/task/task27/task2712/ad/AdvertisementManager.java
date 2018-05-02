package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// объект менеджера
public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        List<Advertisement> videoToBeShown = getVideos(timeSeconds, storage.list(), storage.list().size());
        if (videoToBeShown == null || videoToBeShown.isEmpty())
            throw new NoVideoAvailableException();

        Collections.sort(videoToBeShown, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return (int) ((o1.getAmountPerOneDisplaying() != o2.getAmountPerOneDisplaying()) ?
                        o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying() :
                        o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration() -
                                o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration());
            }
        });

        for (Advertisement i : videoToBeShown) {
            i.revalidate();
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d", i.getName(),
                    i.getAmountPerOneDisplaying(), i.getAmountPerOneDisplaying() * 1000 / i.getDuration()));
        }
    }

    private List<Advertisement> getVideos(int W, List<Advertisement> ad, int n) {
        if (n == 0 || W == 0)
            return null;
        if (ad.get(n - 1).getDuration() / 60 > W || ad.get(n - 1).getHits() <= 0)
            return getVideos(W, ad, n - 1);
        else {
            List<Advertisement> l1 = new ArrayList<>();
            l1.add(ad.get(n - 1));
            List<Advertisement> t = getVideos(W - ad.get(n - 1).getDuration() / 60, ad, n - 1);
            if (t != null) l1.addAll(t);
            List<Advertisement> l2 = getVideos(W, ad, n - 1);
            if (getTotalAmount(l1) == getTotalAmount(l2))
                if (getTotalTime(l1) == getTotalTime(l2)) {
                    return (l1.size() < (l2 != null ? l2.size() : Integer.MAX_VALUE)) ? l1 : l2;
                } else
                    return (getTotalTime(l1) > getTotalTime(l2)) ? l1 : l2;
            else return (getTotalAmount(l1) > getTotalAmount(l2)) ? l1 : l2;
        }
    }

    private long getTotalAmount(List<Advertisement> ad) {
        long totalAmount = 0;
        if (ad == null) return totalAmount;
        for (Advertisement a : ad) {
            totalAmount += a.getAmountPerOneDisplaying();
        }
        return totalAmount;
    }

    private int getTotalTime(List<Advertisement> ad) {
        int totalTime = 0;
        if (ad == null) return totalTime;
        for (Advertisement a : ad) {
            totalTime += a.getDuration() / 60;
        }
        return totalTime;
    }
}
