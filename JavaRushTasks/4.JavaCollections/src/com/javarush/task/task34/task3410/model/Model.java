package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

//  Этот класс будет отвечать за модель нашей игры.
public class Model {
    // это размер ячейки игрового поля
    public final static int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }
}
