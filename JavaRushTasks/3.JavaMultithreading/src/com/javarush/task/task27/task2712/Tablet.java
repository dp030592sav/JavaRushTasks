package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Order;

import java.io.IOException;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

public class Tablet {
    public final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    Tablet(int number) {
        this.number = number;
    }

    public void createOrder() {
        try {
            Order order = new Order(this);
        } catch (IOException e) {
            logger.log(SEVERE, "Console is unavailable.");
        }
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
