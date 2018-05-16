package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Order;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

// Observer(слушатель) для планшетов
public class OrderManager implements Observer {
    private LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public OrderManager() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void update(Observable o, Object object) {
        Order order = (Order) object;
        orderQueue.add(order);
    }
}
