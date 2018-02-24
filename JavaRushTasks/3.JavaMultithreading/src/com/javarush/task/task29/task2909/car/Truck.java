package com.javarush.task.task29.task2909.car;

/**
 * Created by user on 22.02.2018.
 */
public class Truck extends Car {

    public Truck(int numberOfPassengers) {
        super(0, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        return Car.MAX_TRUCK_SPEED;
    }
}
