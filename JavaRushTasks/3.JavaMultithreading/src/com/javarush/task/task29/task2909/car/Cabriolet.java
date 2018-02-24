package com.javarush.task.task29.task2909.car;

/**
 * Created by user on 22.02.2018.
 */
public class Cabriolet extends Car {
    public Cabriolet(int numberOfPassengers) {
        super(2, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        return Car.MAX_CABRIOLET_SPEED;
    }
}
