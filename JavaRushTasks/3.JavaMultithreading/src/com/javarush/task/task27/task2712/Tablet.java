package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Order;
import java.util.Observable;

import java.io.IOException;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

public class Tablet extends Observable {
    public final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    Tablet(int number) {
        this.number = number;
    }

    public Order createOrder() {
        Order order = null;

        try {
            order = new Order(this);
            setChanged();
            notifyObservers(order);
        } catch (IOException e) {
            logger.log(SEVERE, "Console is unavailable.");
        }

        return order;
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }

}
