package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;

public class Tablet {
    public final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());
    private LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();

     Tablet(int number) {
        this.number = number;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }


    public Order createOrder() {
        Order order = null;

        try {
            order = new Order(this);
            if (!order.isEmpty()) {
                queue.add(order);
                new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
            }
        } catch (IOException e) {
            logger.log(SEVERE, "Console is unavailable.");
        } catch (NoVideoAvailableException e) {
            logger.log(INFO, String.format("No video is available for the order %s", order));
        }

        return order;
    }

    public void createTestOrder() {
        TestOrder order = null;

        try {
            order = new TestOrder(this);
            insideOrder(order);
        } catch (IOException e) {
            logger.log(SEVERE, "Console is unavailable.");
        } catch (NoVideoAvailableException e) {
            logger.log(INFO, String.format("No video is available for the order %s", order));
        }
    }

    private void insideOrder(TestOrder order) {
        if (!order.isEmpty()) {
            queue.add(order);
            new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
        }
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }

}
