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
        List<Advertisement> advertisements = storage.list();

        Collections.sort(advertisements, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                int res = 0;
                if (o1.getAmountPerOneDisplaying() != o2.getAmountPerOneDisplaying())
                    res = new Long(o2.getAmountPerOneDisplaying()).compareTo(new Long(o1.getAmountPerOneDisplaying()));
                else if (o1.getDuration() != o2.getDuration())
                    res = new Long(o2.getDuration()).compareTo(new Long(o1.getDuration()));
                return res;
            }
        });

        List<Advertisement> selectedVideos = selectionVideos(advertisements, 0, timeSeconds);

        for (Advertisement i : selectedVideos) {
            i.revalidate();
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d", i.getName(),
                    i.getAmountPerOneDisplaying(), i.getAmountPerOneDisplaying() * 1000 / i.getDuration()));
        }
    }

    private List<Advertisement> selectionVideos(List<Advertisement> availableVideos, int index, int remainderOfTime) {
        if (remainderOfTime == 0 || index >= availableVideos.size())
            return new ArrayList<Advertisement>();
        else if (availableVideos.get(index).getDuration() > remainderOfTime || availableVideos.get(index).getHits() <= 0)
            return selectionVideos(availableVideos, index + 1, remainderOfTime);
        else {
            List<Advertisement> selectedVideos = new ArrayList<>();
            selectedVideos.add(availableVideos.get(index));

            selectedVideos.addAll(selectionVideos(availableVideos, index + 1,
                    remainderOfTime - availableVideos.get(index).getDuration()));
            return selectedVideos;
        }
    }

//    public void processVideos() throws NoVideoAvailableException {
//        List<Advertisement> videoToBeShown = getVideos(timeSeconds, storage.list(), storage.list().size());
//        if (videoToBeShown == null || videoToBeShown.isEmpty())
//            throw new NoVideoAvailableException();
//
//        Collections.sort(videoToBeShown, new Comparator<Advertisement>() {
//            @Override
//            public int compare(Advertisement o1, Advertisement o2) {
//                return (int) ((o1.getAmountPerOneDisplaying() != o2.getAmountPerOneDisplaying()) ?
//                        o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying() :
//                        o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration() -
//                                o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration());
//            }
//        });
//
//        for (Advertisement i : videoToBeShown) {
//            i.revalidate();
//            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d", i.getName(),
//                    i.getAmountPerOneDisplaying(), i.getAmountPerOneDisplaying() * 1000 / i.getDuration()));
//        }
//    }
//
//    private List<Advertisement> getVideos(int W, List<Advertisement> ad, int n) {
//        if (n == 0 || W == 0)
//            return null;
//        if (ad.get(n - 1).getDuration() / 60 > W || ad.get(n - 1).getHits() <= 0)
//            return getVideos(W, ad, n - 1);
//        else {
//            List<Advertisement> l1 = new ArrayList<>();
//            l1.add(ad.get(n - 1));
//            List<Advertisement> t = getVideos(W - ad.get(n - 1).getDuration() / 60, ad, n - 1);
//            if (t != null) l1.addAll(t);
//            List<Advertisement> l2 = getVideos(W, ad, n - 1);
//            if (getTotalAmount(l1) == getTotalAmount(l2))
//                if (getTotalTime(l1) == getTotalTime(l2)) {
//                    return (l1.size() < (l2 != null ? l2.size() : Integer.MAX_VALUE)) ? l1 : l2;
//                } else
//                    return (getTotalTime(l1) > getTotalTime(l2)) ? l1 : l2;
//            else return (getTotalAmount(l1) > getTotalAmount(l2)) ? l1 : l2;
//        }
//    }
//
//    private long getTotalAmount(List<Advertisement> ad) {
//        long totalAmount = 0;
//        if (ad == null) return totalAmount;
//        for (Advertisement a : ad) {
//            totalAmount += a.getAmountPerOneDisplaying();
//        }
//        return totalAmount;
//    }
//
//    private int getTotalTime(List<Advertisement> ad) {
//        int totalTime = 0;
//        if (ad == null) return totalTime;
//        for (Advertisement a : ad) {
//            totalTime += a.getDuration() / 60;
//        }
//        return totalTime;
//    }
}
